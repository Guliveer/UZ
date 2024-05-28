(* Wykorzystując język programowania OCaml napisz program
wyznaczający Największy Wspólny Dzielnik (NWD). *)


(* 'If' method *)
let rec gcd_if a b =
  if b = 0 then
    a
  else
    gcd_if b (a mod b);;


(* 'Match' method *)
let rec gcd_match a b =
  match b with
  | 0 -> a
  | _ -> gcd_match b (a mod b);;

let result a b =
  print_endline ("NWD(" ^ string_of_int a ^ ", " ^ string_of_int b ^ ") = " ^ string_of_int (gcd a b));;

for a = 25 to 30 do
  for b = 5 to 7 do
    result a b
  done
done