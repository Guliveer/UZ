50 * 50;;
6.28;;
"This is really disco!";;
true;;

let u = [1; 2; 3; 4];;
["this"; "is"; "murzyn"];;
9 :: u;;

2 * if "hello" = "dziendobry" then 3 else 5;;

let x = 50;;
x * x;;
let y = 60 in y*y;;
let a = 1 in
let b = 2 in 
a + b;;

let dummy = "hi" = "hi";;

let square x = x * x;;
square 50;;
(fun x -> x * x) 40;;

let cat a b = a ^ " " ^ b;;
cat "ha" "ha";;
let cat_hi = cat "hi";;
cat_hi "friend";;

List.map;;
List.map (fun x -> x * x);;
List.map (fun x -> x * x) [0; 1; 2; 3; 4; 5];;

let rec range lo hi =
  if lo > hi then
    []
  else
    lo :: range (lo + 1) hi;;
range 2 6;;

2.0 +. 2.0;;
float_of_int 1 +. 2.5;;

[];;
[1; 2; 3];;
[false; false; true];;
[[1; 2]; [3]; [4; 5; 6]];;
1 :: [2; 3; 4];;

let rec sum u =
	match u with
	| [] -> 0
	| x :: v -> x + sum v;;
sum [1; 4; 3; 2; 5];;

let rec length u =
	match u with
	| [] -> 0
	| _ :: v -> 1 + length v;;
length [1; 2; 3; 4];;
length ["cow"; "sheep"; "cat"];;
length [[]];;

let g' x = match x with
	| "foo" -> 1
	| "bar" -> 2
	| "baz" -> 3
	| "qux" -> 4
	| _ -> 0;;
fun i -> match i with 0 -> 1;;

(1, "one", 'K');;
([], false);;
let snd p =
	match p with
	| (_, y) -> y;;
snd (42, "apple");;

type primary_colour = Red | Green | Blue;;
[Red; Blue; Red];;
let colour_to_rgb colour =
	match colour with
	| Red -> (0xff, 0, 0)
	| Green -> (0, 0xff, 0)
	| Blue -> (0, 0, 0xff);;

type person = {
	first_name : string;
	surname : string;
	age : int
  };;

let gerard = {
  first_name = "Gérard";
  surname = "Huet";
  age = 40
  };;
let s = gerard.surname;;
let is_teenager person =
	match person with
	| { age = x; _ } -> 13 <= x && x <= 19;;
is_teenager gerard;;

type 'a tree =
  | Leaf
  | Node of 'a tree * 'a * 'a tree;;
let t =
	Node (Node (Leaf, 1, Leaf), 2, Node (Node (Leaf, 3, Leaf), 4, Leaf));;

let rec total t = match t with
  | Leaf -> 0
  | Node (l, x, r) -> total l + x + total r ;;
let rec flip t = match t with
  | Leaf -> Leaf
  | Node (l, x, r) -> Node (flip r, x, flip l) ;;
