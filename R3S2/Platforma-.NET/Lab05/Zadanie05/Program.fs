module Zadanie05.Program

open System

/// Typy zadan, ktore agent potrafi przetworzyc.
/// AddJob niesie wlasciwa robote, GetCount jest typu request/response,
/// Stop konczy petle agenta.
type Message =
    | AddJob   of name: string * work: (unit -> int)
    | GetCount of AsyncReplyChannel<int>
    | Stop     of AsyncReplyChannel<unit>

/// Agent przetwarzajacy zadania w petli. Wewnatrz `loop` mamy
/// niemutowalny `processed: int` przekazywany rekurencyjnie -
/// to typowy F#-owy wzorzec "stan jako parametr petli".
let mkAgent () =
    MailboxProcessor<Message>.Start(fun inbox ->
        let rec loop processed =
            async {
                let! msg = inbox.Receive()
                match msg with
                | AddJob (name, work) ->
                    try
                        let sw = Diagnostics.Stopwatch.StartNew()
                        let result = work ()
                        sw.Stop()
                        printfn
                            "[agent] zadanie '%s' -> %d  (%dms, kolejka: %d)"
                            name result sw.ElapsedMilliseconds inbox.CurrentQueueLength
                    with ex ->
                        printfn "[agent] BLAD w '%s': %s" name ex.Message
                    return! loop (processed + 1)

                | GetCount reply ->
                    reply.Reply processed
                    return! loop processed

                | Stop reply ->
                    printfn "[agent] zatrzymuje sie po %d zadaniach." processed
                    reply.Reply ()
                    // brak return!  -> petla sie konczy
            }
        loop 0)

[<EntryPoint>]
let main _ =
    printfn "=== Zadanie 05: MailboxProcessor ==="
    let agent = mkAgent ()

    // Producent zglasza zadania - rozne czasy wykonania.
    agent.Post(AddJob("suma 1..1000",     fun () -> [1..1000] |> List.sum))
    agent.Post(AddJob("kwadrat 12345",    fun () -> 12345 * 12345))
    agent.Post(AddJob("dlugie obliczenie", fun () ->
        Threading.Thread.Sleep 200
        [1..1_000_000] |> List.sumBy (fun i -> i % 7)))
    agent.Post(AddJob("celowy blad",      fun () -> failwith "ups"))
    agent.Post(AddJob("fib(20)", fun () ->
        let rec fib n = if n < 2 then n else fib (n-1) + fib (n-2)
        fib 20))

    // Synchroniczne zapytanie o stan - czeka az kolejka sie przerobi do tej wiadomosci.
    let count = agent.PostAndReply(GetCount)
    printfn "[main]  agent przetworzyl: %d zadan." count

    // Eleganckie zatrzymanie.
    agent.PostAndReply(Stop)
    (agent :> IDisposable).Dispose()
    0
