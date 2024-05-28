(* Sprawdzenie czy podane słowo jest palindromem (czytane od tyłu tak samo jak od przodu) *)

(* Funkcja *)
let is_palindrome x =
  let len = String.length x in
  let rec loop i =
    if i >= len / 2 then
      Printf.printf "Is palindrome\n"
    else if x.[i] <> x.[len - i - 1] then
      Printf.printf "Is not palindrome\n"
    else
      loop (i + 1)
  in
  loop 0;;


(* Podanie słowa *)
let word = "kajak";;

(* Wypisanie wyniku *)
Printf.printf "\n";
Printf.printf "Word:\t%s" word;
Printf.printf "\n";
is_palindrome word;
Printf.printf "\n";