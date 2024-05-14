(* Wykorzystując język programowania OCaml napisz program, który
dokona faktoryzacji podanej liczby. Sprawdź poprawność działania
programu dla liczb z przedziału od 2 do 1000 *)

(* Funkcja do faktoryzacji liczby *)
let rec factorize n divisor acc =
  if n = 1 then acc
  else if n mod divisor = 0 then factorize (n / divisor) divisor (divisor :: acc)
  else factorize n (divisor + 1) acc;;

(* Funkcja pomocnicza do wyświetlania faktoryzacji *)
let print_factorization n factors =
  Printf.printf "Fact(%d) = " n;
  List.iter (fun x -> Printf.printf "%d " x) factors;
  Printf.printf "\n";;

(* Funkcja wypisująca wynik *)
let result a b =
  for i = a to b do
    print_factorization i (factorize i 2 [])
  done;;


(* Wyznaczanie wynikow z podanego zakresu *)
result 2 1000
