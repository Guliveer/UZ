namespace Zadanie03

open System

/// Liczba zespolona a + b*i (postac kartezjanska).
type Complex(real: double, imag: double) =
    member _.Real = real
    member _.Imag = imag

    member _.Magnitude = Math.Sqrt(real * real + imag * imag)
    member _.Phase     = Math.Atan2(imag, real)
    member _.Conjugate = Complex(real, -imag)

    static member Zero = Complex(0.0, 0.0)
    static member One  = Complex(1.0, 0.0)
    static member I    = Complex(0.0, 1.0)

    static member FromPolar(r: double, phi: double) =
        Complex(r * Math.Cos phi, r * Math.Sin phi)

    static member (+) (a: Complex, b: Complex) =
        Complex(a.Real + b.Real, a.Imag + b.Imag)

    static member (-) (a: Complex, b: Complex) =
        Complex(a.Real - b.Real, a.Imag - b.Imag)

    static member (*) (a: Complex, b: Complex) =
        // (a+bi)(c+di) = (ac - bd) + (ad + bc)i
        Complex(a.Real * b.Real - a.Imag * b.Imag,
                a.Real * b.Imag + a.Imag * b.Real)

    static member (/) (a: Complex, b: Complex) =
        // mnozenie przez sprzezenie mianownika
        let denom = b.Real * b.Real + b.Imag * b.Imag
        if denom = 0.0 then invalidArg "b" "Dzielenie przez zero zespolone."
        Complex((a.Real * b.Real + a.Imag * b.Imag) / denom,
                (a.Imag * b.Real - a.Real * b.Imag) / denom)

    static member (~-) (a: Complex) = Complex(-a.Real, -a.Imag)

    // Mieszane operacje ze skalarem (double).
    static member (+) (a: Complex, s: double) = Complex(a.Real + s, a.Imag)
    static member (+) (s: double, a: Complex) = Complex(a.Real + s, a.Imag)
    static member (*) (a: Complex, s: double) = Complex(a.Real * s, a.Imag * s)
    static member (*) (s: double, a: Complex) = Complex(a.Real * s, a.Imag * s)

    static member op_Equality (a: Complex, b: Complex) =
        a.Real = b.Real && a.Imag = b.Imag

    override this.Equals(o) =
        match o with
        | :? Complex as c -> this.Real = c.Real && this.Imag = c.Imag
        | _ -> false

    override _.GetHashCode() = HashCode.Combine(real, imag)

    override _.ToString() =
        if imag >= 0.0 then sprintf "%g + %gi" real imag
        else sprintf "%g - %gi" real (abs imag)
