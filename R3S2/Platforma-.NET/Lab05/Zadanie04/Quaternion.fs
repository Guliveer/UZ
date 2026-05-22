namespace Zadanie04

open System

/// Kwaternion q = w + x*i + y*j + z*k.
/// Mnozenie: i*i = j*j = k*k = i*j*k = -1, niekomutatywne.
type Quaternion(w: double, x: double, y: double, z: double) =
    member _.W = w
    member _.X = x
    member _.Y = y
    member _.Z = z

    member _.Norm      = Math.Sqrt(w*w + x*x + y*y + z*z)
    member _.Conjugate = Quaternion(w, -x, -y, -z)

    member this.Inverse =
        let n2 = w*w + x*x + y*y + z*z
        if n2 = 0.0 then invalidOp "Kwaternion zerowy nie ma odwrotnosci."
        let c = this.Conjugate
        Quaternion(c.W / n2, c.X / n2, c.Y / n2, c.Z / n2)

    member this.Normalized =
        let n = this.Norm
        if n = 0.0 then invalidOp "Nie mozna znormalizowac zerowego kwaterniona."
        Quaternion(w / n, x / n, y / n, z / n)

    static member Zero     = Quaternion(0.0, 0.0, 0.0, 0.0)
    static member One      = Quaternion(1.0, 0.0, 0.0, 0.0)
    static member Identity = Quaternion.One
    static member I        = Quaternion(0.0, 1.0, 0.0, 0.0)
    static member J        = Quaternion(0.0, 0.0, 1.0, 0.0)
    static member K        = Quaternion(0.0, 0.0, 0.0, 1.0)

    /// Kwaternion obrotu wokol osi (ax, ay, az) o kat `angle` (radiany).
    static member FromAxisAngle(ax: double, ay: double, az: double, angle: double) =
        let len = Math.Sqrt(ax*ax + ay*ay + az*az)
        if len = 0.0 then invalidArg "axis" "Os zerowa."
        let s = Math.Sin(angle / 2.0) / len
        Quaternion(Math.Cos(angle / 2.0), ax * s, ay * s, az * s)

    static member (+) (a: Quaternion, b: Quaternion) =
        Quaternion(a.W + b.W, a.X + b.X, a.Y + b.Y, a.Z + b.Z)

    static member (-) (a: Quaternion, b: Quaternion) =
        Quaternion(a.W - b.W, a.X - b.X, a.Y - b.Y, a.Z - b.Z)

    static member (~-) (a: Quaternion) =
        Quaternion(-a.W, -a.X, -a.Y, -a.Z)

    /// Mnozenie Hamiltona: NIEKOMUTATYWNE.
    static member (*) (a: Quaternion, b: Quaternion) =
        Quaternion(
            a.W*b.W - a.X*b.X - a.Y*b.Y - a.Z*b.Z,
            a.W*b.X + a.X*b.W + a.Y*b.Z - a.Z*b.Y,
            a.W*b.Y - a.X*b.Z + a.Y*b.W + a.Z*b.X,
            a.W*b.Z + a.X*b.Y - a.Y*b.X + a.Z*b.W)

    static member (/) (a: Quaternion, b: Quaternion) = a * b.Inverse

    // Skalary:
    static member (*) (a: Quaternion, s: double) =
        Quaternion(a.W * s, a.X * s, a.Y * s, a.Z * s)
    static member (*) (s: double, a: Quaternion) =
        Quaternion(a.W * s, a.X * s, a.Y * s, a.Z * s)
    static member (/) (a: Quaternion, s: double) =
        if s = 0.0 then invalidArg "s" "Dzielenie przez 0."
        Quaternion(a.W / s, a.X / s, a.Y / s, a.Z / s)

    static member op_Equality (a: Quaternion, b: Quaternion) =
        a.W = b.W && a.X = b.X && a.Y = b.Y && a.Z = b.Z

    override this.Equals(o) =
        match o with
        | :? Quaternion as q -> this.W = q.W && this.X = q.X && this.Y = q.Y && this.Z = q.Z
        | _ -> false

    override _.GetHashCode() = HashCode.Combine(w, x, y, z)

    override _.ToString() =
        sprintf "(%g, %gi, %gj, %gk)" w x y z
