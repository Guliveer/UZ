module Zadanie06.Program

open Zadanie06.Lexer
open Zadanie06.Parser

let demo (input: string) =
    try
        let tokens = tokenize input
        let ast = parse tokens
        let value = eval ast
        printfn "  '%s'" input
        printfn "    tokeny = %A" tokens
        printfn "    AST    = %A" ast
        printfn "    wynik  = %g" value
    with ex ->
        printfn "  '%s' -> BLAD: %s" input ex.Message

[<EntryPoint>]
let main _ =
    printfn "=== Zadanie 06: lekser + parser wyrazen arytmetycznych ==="

    demo "1 + 2 * 3"
    demo "(1 + 2) * 3"
    demo "-3 + 4"
    demo "2.5 * (3 + -1)"
    demo "10 / 2 / 5"               // lewostronna lacznosc
    demo "((1 + 2) * (3 - 4)) / 0.5"
    demo "1 + + 2"                   // blad parsera
    demo "1 / 0"                      // blad ewaluacji
    0
