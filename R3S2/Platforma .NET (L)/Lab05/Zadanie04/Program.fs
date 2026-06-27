module Zadanie04.Program

open System
open Zadanie04

[<EntryPoint>]
let main _ =
    printfn "=== Zadanie 04: Kwaterniony ==="
    let q1 = Quaternion(1.0, 2.0, 3.0, 4.0)
    let q2 = Quaternion(2.0, -1.0, 0.5, 1.0)

    printfn "q1                = %O" q1
    printfn "q2                = %O" q2
    printfn "q1 + q2           = %O" (q1 + q2)
    printfn "q1 - q2           = %O" (q1 - q2)
    printfn "q1 * q2           = %O" (q1 * q2)
    printfn "q2 * q1           = %O   (niekomutatywne!)" (q2 * q1)
    printfn "q1 / q2           = %O" (q1 / q2)
    printfn "-q1               = %O" (-q1)
    printfn "q1*               = %O   (sprzezenie)" q1.Conjugate
    printfn "|q1|              = %f" q1.Norm
    printfn "q1^-1             = %O" q1.Inverse
    printfn "q1 * q1^-1        = %O   (~ identycznosc)" (q1 * q1.Inverse)

    // i*j = k, j*i = -k -> klasyczny test poprawnosci.
    printfn "i*j               = %O   (powinno byc k)" (Quaternion.I * Quaternion.J)
    printfn "j*i               = %O   (powinno byc -k)" (Quaternion.J * Quaternion.I)

    // Obrot wokol osi Z o 90 stopni.
    let rot = Quaternion.FromAxisAngle(0.0, 0.0, 1.0, Math.PI / 2.0)
    printfn "rotZ(90 deg)      = %O   (znormalizowany)" rot
    printfn "|rotZ|            = %f" rot.Norm
    0
