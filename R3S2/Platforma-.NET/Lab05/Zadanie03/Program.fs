module Zadanie03.Program

open Zadanie03

[<EntryPoint>]
let main _ =
    printfn "=== Zadanie 03: Liczby zespolone ==="
    let z1 = Complex(3.0, 4.0)
    let z2 = Complex(1.0, -2.0)

    printfn "z1            = %O" z1
    printfn "z2            = %O" z2
    printfn "z1 + z2       = %O" (z1 + z2)
    printfn "z1 - z2       = %O" (z1 - z2)
    printfn "z1 * z2       = %O" (z1 * z2)
    printfn "z1 / z2       = %O" (z1 / z2)
    printfn "-z1           = %O" (-z1)
    printfn "|z1|          = %f" z1.Magnitude
    printfn "arg(z1)       = %f rad" z1.Phase
    printfn "z1*           = %O   (sprzezenie)" z1.Conjugate
    printfn "z1 + 2.0      = %O" (z1 + 2.0)
    printfn "0.5 * z2      = %O" (0.5 * z2)
    printfn "i * i         = %O   (powinno byc -1)" (Complex.I * Complex.I)
    let polar = Complex.FromPolar(2.0, System.Math.PI / 2.0)
    printfn "FromPolar(2, pi/2) = %O   (~ 2i)" polar
    0
