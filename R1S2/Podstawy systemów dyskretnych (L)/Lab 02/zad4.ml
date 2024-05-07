(* Znajdź rekurencyjną i nierekurencyjną definicję ciągu *)
(* 2, 7, 12, 17, 22, 27, ... *)


(* Rekurencyjna definicja *)
let rec a_recursive n =
  match n with
  | 1 -> 2
  | _ -> a_recursive (n - 1) + 5;;

  
(* Nierekurencyjna definicja *)
let a_non_recursive n = 2 + (n - 1) * 5;;


(* Wypisanie wyników *)
for i = 1 to 10 do
  let result_recursive = a_recursive i in
  let result_non_recursive = a_non_recursive i in
  print_endline ("Rekurencyjna: a(" ^ string_of_int i ^ ") = " ^ string_of_int result_recursive);
  print_endline ("Nierekurencyjna: a(" ^ string_of_int i ^ ") = " ^ string_of_int result_non_recursive)
done;;
