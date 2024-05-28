(* Suma nieparzystych elementów z listy *)

(* Funkcja *)
let rec sum_odd lst =
  match lst with
  | [] -> 0
  | h :: t -> if h mod 2 = 1 then h + sum_odd t
              else sum_odd t;;


(* Podanie listy *)
let list = [0; 1; 2; 3; 4; 5; 6; 7];;

(* Wypisanie listy *)
Printf.printf "\n";
List.iter (fun x -> Printf.printf "%d " x) list;
Printf.printf "\n";
Printf.printf "Sum of odd elements: %d " (sum_odd list);
Printf.printf "\n";