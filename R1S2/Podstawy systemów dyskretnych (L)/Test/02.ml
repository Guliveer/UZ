(* Funkcja zwraca trzeci od końca element listy *)

(* Funkcja *)
let third_last lst =
  if List.length lst < 3 then
    failwith "List has less than 3 elements"
  else
    (List.nth (List.rev lst) 2);;


(* Podanie listy *)
let list = [1; 2; 3; 4; 5; "dupa"; 7; 8];;

(* Wypisanie wyniku *)
Printf.printf "\n";
List.iter (fun x -> Printf.printf "%d " x) list;
Printf.printf "\n";
Printf.printf "Third last element: %d" (third_last list);
Printf.printf "\n";;