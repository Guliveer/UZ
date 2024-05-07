(* Funkcja wyznacza t(3), t(4) i t(5) dla tak zdefiniowanego ciągu rekurencyjnego *)

let rec t n =
  match n with
  | 1 -> 1
  | 2 -> 1
  | _ -> t (n - 1) + 2 * t (n-2);;

for n = 1 to 50 do
  let result = t n in
  print_endline ("t(" ^ string_of_int n ^ ") = " ^ string_of_int result)
done;;
