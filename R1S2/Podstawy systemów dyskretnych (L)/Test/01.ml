(* Każdy element z listy pomnożony przez 4 oraz wypisanie listy *)

(* Funkcja *)
let rec multiply_by_4 lst =
  match lst with
  | [] -> []
  | h :: t -> (h * 4) :: (multiply_by_4 t);;


(* Podanie listy *)
let list = [1; 2; 3; 4; 5] in;;

(* Wypisanie listy *)
Printf.printf "\n";
List.iter (fun x -> Printf.printf "\t%d" x) list;
Printf.printf "\n";
List.iter (fun x -> Printf.printf "\t%d" x) (multiply_by_4 list);
Printf.printf "\n";
