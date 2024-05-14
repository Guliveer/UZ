(* Wykorzystując język programowania OCaml napisz program
wyznaczający Najmniejszą Wspólną Wielokrotność (NWW). *)

let rec gcd a b =
  match b with
  | 0 -> a
  | _ -> gcd b (a mod b);;

let lcm a b = (a * b) / (gcd a b);;

let result a b =
  print_endline ("NWW(" ^ string_of_int a ^ ", " ^ string_of_int b ^ ") = " ^ string_of_int (lcm a b));;

for a = 25 to 30 do
  for b = 5 to 7 do
    result a b
  done
done
