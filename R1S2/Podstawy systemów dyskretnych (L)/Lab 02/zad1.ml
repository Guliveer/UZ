(* funkcja wyznacza t(2), t(3) oraz t(4) dla zdefiniowanych ciągow *)
(* a: t(1) = 3; t(n) = t(n-1)+4 *)
(* b: t(1) = 1; t(n) = t(n-1)+n *)
(* c: t(1) = 0; t(n) = 2*t(n-1)+1 *)


(* a *)
let rec t n =
  match n with
  | 1 -> 3
  | _ -> t (n - 1) + 4;;

for n = 1 to 100 do
  let result = t n in
  print_endline ("t(" ^ string_of_int n ^ ") = " ^ string_of_int result)
done;;


(* b *)
let rec t n =
  match n with
  | 1 -> 1
  | _ -> t (n - 1) + n;;

for n = 1 to 100 do
  let result = t n in
  print_endline ("t(" ^ string_of_int n ^ ") = " ^ string_of_int result)
done;;


(* c *)
let rec t n =
  match n with
  | 1 -> 1
  | _ -> 2 * t (n - 1) + 1;;

for n = 1 to 100 do
  let result = t n in
  print_endline ("t(" ^ string_of_int n ^ ") = " ^ string_of_int result)
done;;

