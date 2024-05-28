(* Silnia liczby w formie rekurencyjnej *)

(* Funkcja *)
let rec factorial n =
  if n = 0 then 1
  else n * factorial (n - 1);;


(* Podanie argumentu *)
let x = 5;;

(* Wypisanie wyniku *)
Printf.printf "\n";
Printf.printf "%d! = %d" x (factorial x);
Printf.printf "\n";
Printf.printf "%d! = %d" (x+1) (factorial (x+1));
Printf.printf "\n";
Printf.printf "%d! = %d" (x+2) (factorial (x+2));
Printf.printf "\n";;