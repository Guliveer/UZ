(* Funkcja NWD (Największy Wspólny Dzielnik) *)

(* Funkcja *)
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

(* Podanie argumentów *)
let x = 46;;
let y = 92;;

(* Wypisanie wyniku *)
Printf.printf "\n";
Printf.printf "[If]\tNWD(%d, %d) = %d\n" x y (gcd_if x y);
Printf.printf "[Match]\tNWD(%d, %d) = %d\n" x y (gcd_match x y);
Printf.printf "\n";