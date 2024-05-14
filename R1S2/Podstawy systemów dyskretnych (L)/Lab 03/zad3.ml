(* Wykorzystując język programowania OCaml napisz program, który
wyznaczy liczby pierwsze z podanego przedziału [0, 20]. *)

(* Funkcja do wygenerowania listy liczb od 2 do n *)
let rec range start limit =
  if start > limit then []
  else start :: (range (start + 1) limit);;


(* Funkcja do usuwania wielokrotności liczby z listy *)
let rec remove_multiples n lst =
  match lst with
  | [] -> []
  | h :: t -> if h mod n = 0 then remove_multiples n t
              else h :: (remove_multiples n t);;


(* Funkcja implementująca sito Eratostenesa *)
let rec sieve lst =
  match lst with
  | [] -> []
  | h :: t -> h :: (sieve (remove_multiples h t));;


(* Funkcja do wyznaczania liczb pierwszych w danym przedziale *)
let primes_in_range start limit =
  let numbers = range 2 limit in
  let primes = sieve numbers in
  List.filter (fun x -> x >= start) primes;;


(* Funkcja do wypisywania wynikow *)
let result a b = 
  print_endline ("Liczby pierwsze w przedziale od " ^ string_of_int a ^ " do " ^ string_of_int b ^ ":");
  List.iter (fun x -> Printf.printf "%d " x) (primes_in_range a b);
  Printf.printf "\n";;


(* Wyznaczanie wynikow z podanego zakresu *)
result 0 50
