module Zadanie06.Parser

open Zadanie06.Lexer

/// AST dla wyrazen arytmetycznych.
type Expr =
    | Num of double
    | Neg of Expr
    | Add of Expr * Expr
    | Sub of Expr * Expr
    | Mul of Expr * Expr
    | Div of Expr * Expr

/// Gramatyka (LL(1), recursive descent):
///   expr   -> term   (('+' | '-') term)*
///   term   -> factor (('*' | '/') factor)*
///   factor -> '-' factor | '(' expr ')' | NUMBER
let parse (tokens: Token list) : Expr =
    let stream = ref tokens

    let peek () =
        match !stream with
        | t :: _ -> t
        | [] -> TEnd

    let advance () =
        match !stream with
        | _ :: rest -> stream := rest
        | [] -> ()

    let expect t =
        if peek () <> t then
            failwithf "Oczekiwano %A, dostano %A." t (peek ())
        advance ()

    let rec parseExpr () =
        let mutable left = parseTerm ()
        let mutable cont = true
        while cont do
            match peek () with
            | TPlus  -> advance (); left <- Add(left, parseTerm ())
            | TMinus -> advance (); left <- Sub(left, parseTerm ())
            | _ -> cont <- false
        left

    and parseTerm () =
        let mutable left = parseFactor ()
        let mutable cont = true
        while cont do
            match peek () with
            | TStar  -> advance (); left <- Mul(left, parseFactor ())
            | TSlash -> advance (); left <- Div(left, parseFactor ())
            | _ -> cont <- false
        left

    and parseFactor () =
        match peek () with
        | TMinus ->
            advance ()
            Neg(parseFactor ())
        | TLParen ->
            advance ()
            let e = parseExpr ()
            expect TRParen
            e
        | TNum v ->
            advance ()
            Num v
        | t -> failwithf "Nieoczekiwany token %A." t

    let result = parseExpr ()
    expect TEnd
    result

/// Ewaluacja AST - kanoniczna rekurencja po dyskryminowanej unii.
let rec eval expr =
    match expr with
    | Num v       -> v
    | Neg e       -> -(eval e)
    | Add (a, b)  -> eval a + eval b
    | Sub (a, b)  -> eval a - eval b
    | Mul (a, b)  -> eval a * eval b
    | Div (a, b)  ->
        let d = eval b
        if d = 0.0 then failwith "Dzielenie przez zero."
        eval a / d
