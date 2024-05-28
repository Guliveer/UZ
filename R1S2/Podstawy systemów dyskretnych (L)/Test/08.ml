(* Z podanej listy wypisać elementy podzielne przez 3 (x mod 3 == 0) *)

(* Funkcja *)
let rec divisible_by_3 lst =
  match lst with
  | [] -> []
  | h :: t -> if h mod 3 = 0 then h :: (divisible_by_3 t)
              else divisible_by_3 t;;

let print_list lst =
  List.iter (fun x -> Printf.printf "%d " x) lst;;

(* Podanie listy *)
let list = [1; 2; 3; 4; 5; 6; 7; 8; 9; 10; 11; 12; 13; 14; 15; 16; 17; 18; 19; 20];;

(* Wypisanie listy *)
Printf.printf "\n";
print_list list;
Printf.printf "\n";
print_list (divisible_by_3 list);
Printf.printf "\n";