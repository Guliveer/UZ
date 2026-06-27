module Zadanie06.Lexer

open System

type Token =
    | TNum    of double
    | TPlus
    | TMinus
    | TStar
    | TSlash
    | TLParen
    | TRParen
    | TEnd

/// Analizator leksykalny: string -> Token list.
/// Pomija biale znaki, czyta liczby (calkowite i z kropka).
let tokenize (input: string) : Token list =
    let mutable i = 0
    let n = input.Length
    let tokens = ResizeArray<Token>()

    while i < n do
        let c = input.[i]
        if Char.IsWhiteSpace c then
            i <- i + 1
        elif Char.IsDigit c || c = '.' then
            let start = i
            while i < n && (Char.IsDigit input.[i] || input.[i] = '.') do
                i <- i + 1
            let lex = input.Substring(start, i - start)
            tokens.Add(TNum(Double.Parse(lex, Globalization.CultureInfo.InvariantCulture)))
        else
            match c with
            | '+' -> tokens.Add TPlus;   i <- i + 1
            | '-' -> tokens.Add TMinus;  i <- i + 1
            | '*' -> tokens.Add TStar;   i <- i + 1
            | '/' -> tokens.Add TSlash;  i <- i + 1
            | '(' -> tokens.Add TLParen; i <- i + 1
            | ')' -> tokens.Add TRParen; i <- i + 1
            | _   -> failwithf "Nieoczekiwany znak '%c' na pozycji %d." c i

    tokens.Add TEnd
    List.ofSeq tokens
