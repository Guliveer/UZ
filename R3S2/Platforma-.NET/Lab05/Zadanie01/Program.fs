// Zadanie 01 — proste rekurencje + slowo kluczowe `match`
// Pokazuje typowe schematy rekurencji w F#: linowa, ogonowa,
// dopasowanie wzorcow do list i drzew binarnych.

module Zadanie01.Program

// 1) Silnia — rekurencja liniowa (NIE ogonowa).
//    Stos rosnie liniowo z n; dla duzych n grozi StackOverflow.
let rec factorial n =
    match n with
    | 0 | 1 -> 1L
    | n when n > 0 -> int64 n * factorial (n - 1)
    | _ -> failwith "factorial: ujemny argument"

// 2) Silnia ogonowa (tail-recursive) z akumulatorem.
//    Kompilator F# przeksztalca to w petle => stala wielkosc stosu.
let factorialTail n =
    let rec loop acc k =
        match k with
        | 0 | 1 -> acc
        | _ -> loop (acc * int64 k) (k - 1)
    if n < 0 then failwith "factorial: ujemny argument"
    else loop 1L n

// 3) Fibonacci — naiwna wersja eksponencjalna (do celow demonstracyjnych).
let rec fibNaive n =
    match n with
    | 0 -> 0
    | 1 -> 1
    | n -> fibNaive (n - 1) + fibNaive (n - 2)

// 4) Fibonacci ogonowy — O(n), dwa akumulatory.
//    Uzywamy int64, bo fib(46) juz przekracza zakres int32.
let fibFast n =
    let rec loop (a: int64) (b: int64) k =
        match k with
        | 0 -> a
        | _ -> loop b (a + b) (k - 1)
    loop 0L 1L n

// 5) NWD (algorytm Euklidesa) — match z guardami.
let rec gcd a b =
    match a, b with
    | a, 0 -> abs a
    | a, b -> gcd b (a % b)

// 6) Potegowanie szybkie — rekurencja logarytmiczna O(log n).
let rec power x n =
    match n with
    | 0 -> 1.0
    | n when n < 0 -> 1.0 / power x (-n)
    | n when n % 2 = 0 ->
        let half = power x (n / 2)
        half * half
    | n -> x * power x (n - 1)

// 7) Operacje na listach — klasyczne wzorce `head :: tail`.
let rec length xs =
    match xs with
    | [] -> 0
    | _ :: tail -> 1 + length tail

let rec sumList xs =
    match xs with
    | [] -> 0
    | h :: t -> h + sumList t

let rec mapList f xs =
    match xs with
    | [] -> []
    | h :: t -> f h :: mapList f t

let rec reverseList xs =
    let rec loop acc = function
        | [] -> acc
        | h :: t -> loop (h :: acc) t
    loop [] xs

// 8) Drzewo binarne — rekurencyjny ADT i przejscie in-order.
type Tree<'a> =
    | Leaf
    | Node of 'a * Tree<'a> * Tree<'a>

let rec inorder tree =
    match tree with
    | Leaf -> []
    | Node (v, l, r) -> inorder l @ [v] @ inorder r

let rec depth tree =
    match tree with
    | Leaf -> 0
    | Node (_, l, r) -> 1 + max (depth l) (depth r)

[<EntryPoint>]
let main _ =
    printfn "=== Zadanie 01: rekurencje i `match` ==="

    printfn "factorial 10        = %d" (factorial 10)
    printfn "factorialTail 20    = %d" (factorialTail 20)

    printfn "fibNaive 10         = %d" (fibNaive 10)
    printfn "fibFast 50          = %d" (fibFast 50)

    printfn "gcd 462 1071        = %d" (gcd 462 1071)
    printfn "power 2.0 10        = %f" (power 2.0 10)
    printfn "power 2.0 -3        = %f" (power 2.0 -3)

    let xs = [3; 1; 4; 1; 5; 9; 2; 6]
    printfn "length %A           = %d" xs (length xs)
    printfn "sumList %A          = %d" xs (sumList xs)
    printfn "mapList ((*) 2)     = %A" (mapList ((*) 2) xs)
    printfn "reverseList         = %A" (reverseList xs)

    let tree =
        Node(5,
            Node(3, Node(1, Leaf, Leaf), Node(4, Leaf, Leaf)),
            Node(8, Leaf, Node(9, Leaf, Leaf)))
    printfn "inorder tree        = %A" (inorder tree)
    printfn "depth tree          = %d" (depth tree)
    0
