module Zadanie02.Program

open Zadanie02

[<EntryPoint>]
let main _ =
    printfn "=== Zadanie 02: Ulamki zwykle ==="
    let a = Fraction(1, 2)
    let b = Fraction(2, 6)   // -> 1/3
    let c = Fraction(-3, -9) // znak normalizowany -> 1/3

    printfn "a       = %O" a
    printfn "b       = %O  (po skroceniu)" b
    printfn "c       = %O  (znak po normalizacji)" c
    printfn "a + b   = %O" (a + b)
    printfn "a - b   = %O" (a - b)
    printfn "a * b   = %O" (a * b)
    printfn "a / b   = %O" (a / b)
    printfn "-a      = %O" (-a)
    printfn "a + 2   = %O" (a + 2)
    printfn "3 * a   = %O" (3 * a)
    printfn "a < b   = %b" (a < b)
    printfn "a = c   = %b" (a.Equals c)
    printfn "double(a/b) = %f" ((a / b).ToDouble())
    0
