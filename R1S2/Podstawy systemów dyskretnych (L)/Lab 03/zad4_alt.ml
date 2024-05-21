(* Funkcja generująca liczby pierwsze do danego limitu za pomocą sita Eratostenesa *)
let sieve limit =
  let is_prime = Array.make (limit + 1) true in
  is_prime.(0) <- false;
  is_prime.(1) <- false;
  for p = 2 to int_of_float (sqrt (float_of_int limit)) do
    if is_prime.(p) then
      let rec mark_multiples i =
        if i <= limit then
          (is_prime.(i) <- false; mark_multiples (i + p))
      in
      mark_multiples (p * p)
  done;
  let primes = ref [] in
  for i = limit downto 2 do
    if is_prime.(i) then primes := i :: !primes
  done;
  !primes

(* Funkcja do faktoryzacji liczby *)
let rec factorize n primes acc =
  match primes with
  | [] -> acc
  | p :: ps ->
    if p * p > n then n :: acc
    else if n mod p = 0 then factorize (n / p) primes (p :: acc)
    else factorize n ps acc

(* Funkcja pomocnicza do wyświetlania faktoryzacji *)
let print_factorization n factors =
  Printf.printf "Fact(%d) = " n;
  List.iter (fun x -> Printf.printf "%d " x) factors;
  Printf.printf "\n"

(* Funkcja wypisująca wynik *)
let result a b =
  let primes = sieve b in
  for i = a to b do
    print_factorization i (List.rev (factorize i primes []))
  done

(* Wyznaczanie wynikow z podanego zakresu *)
result 2 1000
