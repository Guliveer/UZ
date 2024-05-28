(* Podanie liczb parzystych z podanego przedziału [x, y] *)

(* Funkcje *)
let rec even_range x y =
  if x > y then []
  else if x mod 2 = 0 then x :: (even_range (x + 1) y)
  else even_range (x + 1) y;;

let print_range x y =
  List.iter (fun x -> Printf.printf "%d " x) (even_range x y);;


(* Podanie argumentów *)
let x = 1;;
let y = 11;;

(* Wypisanie wyniku *)
Printf.printf "\n";
Printf.printf "Range:\t[%d, %d]" x y;
Printf.printf "\n";
Printf.printf "Result:\t";
print_range x y;
Printf.printf "\n";
