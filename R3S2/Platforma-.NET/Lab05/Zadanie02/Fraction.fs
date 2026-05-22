namespace Zadanie02

open System

/// Ulamek zwykly p/q. Niezmiennik: q > 0 oraz gcd(|p|, q) = 1.
type Fraction(numerator: int64, denominator: int64) =
    static let rec gcd a b =
        if b = 0L then abs a else gcd b (a % b)

    static let normalize (p: int64) (q: int64) =
        if q = 0L then invalidArg "denominator" "Mianownik nie moze byc zerem."
        let sign = if q < 0L then -1L else 1L
        let p, q = sign * p, sign * q
        let d = gcd (abs p) q
        if d = 0L then 0L, 1L else p / d, q / d

    let p, q = normalize numerator denominator

    new(n: int64) = Fraction(n, 1L)
    new(p: int, q: int) = Fraction(int64 p, int64 q)
    new(n: int) = Fraction(int64 n, 1L)

    member _.Numerator = p
    member _.Denominator = q
    member _.ToDouble() = float p / float q

    static member Zero = Fraction(0L, 1L)
    static member One  = Fraction(1L, 1L)

    static member (+) (a: Fraction, b: Fraction) =
        Fraction(a.Numerator * b.Denominator + b.Numerator * a.Denominator,
                 a.Denominator * b.Denominator)

    static member (-) (a: Fraction, b: Fraction) =
        Fraction(a.Numerator * b.Denominator - b.Numerator * a.Denominator,
                 a.Denominator * b.Denominator)

    static member (*) (a: Fraction, b: Fraction) =
        Fraction(a.Numerator * b.Numerator, a.Denominator * b.Denominator)

    static member (/) (a: Fraction, b: Fraction) =
        if b.Numerator = 0L then invalidArg "b" "Dzielenie przez zero."
        Fraction(a.Numerator * b.Denominator, a.Denominator * b.Numerator)

    static member (~-) (a: Fraction) = Fraction(-a.Numerator, a.Denominator)

    // Mieszane: int <-> Fraction
    static member (+) (a: Fraction, n: int) = a + Fraction(n)
    static member (+) (n: int, a: Fraction) = Fraction(n) + a
    static member (*) (a: Fraction, n: int) = a * Fraction(n)
    static member (*) (n: int, a: Fraction) = Fraction(n) * a

    // Porownania
    static member op_Equality (a: Fraction, b: Fraction) =
        a.Numerator = b.Numerator && a.Denominator = b.Denominator

    static member op_LessThan (a: Fraction, b: Fraction) =
        a.Numerator * b.Denominator < b.Numerator * a.Denominator

    override this.Equals(o) =
        match o with
        | :? Fraction as f -> this.Numerator = f.Numerator && this.Denominator = f.Denominator
        | _ -> false

    override _.GetHashCode() = HashCode.Combine(p, q)

    override _.ToString() =
        if q = 1L then string p else sprintf "%d/%d" p q

    interface IComparable with
        member this.CompareTo(o) =
            match o with
            | :? Fraction as f ->
                compare (this.Numerator * f.Denominator) (f.Numerator * this.Denominator)
            | _ -> invalidArg "o" "Nie jest Fraction"
