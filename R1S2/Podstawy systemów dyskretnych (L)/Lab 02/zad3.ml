(* Dany jest ciąg t(n) = 1+3+5+...+(2n+1) *)
(* Zapisz rekurencyjną definicję t(n) *)

let rec t n =
  match n with
  | 1 -> 1
  | _ -> t (n - 1) + (2 * n - 1);;


for n = 1 to 10 do
  let result = t n in
  print_endline ("t(" ^ string_of_int n ^ ") = " ^ string_of_int result)
done;;