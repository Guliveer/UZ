(* Przesunąć wszystkie elementy listy na kolejny indeks, a ostatni element przenieść na początek *)

(* Funkcja *)
let rotate lst =
  match List.rev lst with
  | [] -> []
  | h::t -> h::(List.rev t);;


(* Funkcja pomocnicza do wypisywania listy *)

let print_list lst =
  List.iter (fun x -> Printf.printf "%d " x) lst;;

(* Podanie listy *)
let list = [1; 2; 3; 4; 5];;

(* Wypisanie listy *)
Printf.printf "\n";
print_list list;
Printf.printf "\n";
print_list (rotate list);
Printf.printf "\n";