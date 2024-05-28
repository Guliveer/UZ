(* Liczenie sześcianu danej liczby (x^3) w formie rekurencyjnej *)

(* Funkcja *)
let cube n =
  let result = ref 1 in
  for i = 1 to 3 do
    result := !result * n
  done;
  !result;;

(* Podanie argumentu *)
let x = 5;;

(* Wypisanie wyniku *)
Printf.printf "\n";
Printf.printf "%d^3 = %d" x (cube x);
Printf.printf "\n";
Printf.printf "(%d)^3 = %d" (-x) (cube (-x));
Printf.printf "\n";;