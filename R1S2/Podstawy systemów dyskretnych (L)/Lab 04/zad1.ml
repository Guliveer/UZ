let rec gcd a b =
  if b = 0 then
    a
  else
    gcd b (a mod b);;

let p = 3;;
let q = 7;;
let n = float_of_int(p*q);;
let e = 2;;

let phi = (p-1)*(q-1);;

let rec range e phi =
	if e > phi then
  	[]
	else
  	e :: range (e + 1) phi;;

let list = range e phi;;

let wynik = List.find(fun x -> gcd x phi == 1) list;;

let k = 2;;
phi;;
e;;
let d = (1 + (k*phi))/wynik;;

let msg = 20.00;;

let c = mod_float (msg ** float_of_int(wynik)) n;;

let m = mod_float (c ** float_of_int(d)) n;;