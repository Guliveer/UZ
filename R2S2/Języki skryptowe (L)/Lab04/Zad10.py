# Wyjaśnij na czym polega użycie funkcji lambda i kiedy warto ją stosować?

def main():
    # A lambda function to add two numbers
    add = lambda x, y: x + y
    print("Sum of 5 and 3 is:", add(5, 3))

    # A lambda function to square a number
    square = lambda x: x * x
    print("Square of 4 is:", square(4))

    # Using lambda with the map function
    numbers = [1, 2, 3, 4, 5]
    squared_numbers = list(map(lambda x: x * x, numbers))
    print("Squared numbers:", squared_numbers)

if __name__ == "__main__":
    main()

# - Krótkie i proste funkcje: Kiedy potrzebujesz małej funkcji przez krótki okres czasu i nie jest ona ponownie wykorzystywana gdzie indziej.
# - Funkcje wyższego rzędu: Kiedy przekazujesz prostą funkcję jako argument do funkcji wyższego rzędu, takich jak map(), filter() i sorted().
# - Definicje funkcji inline: Kiedy definiujesz funkcję inline bez potrzeby formalnego definiowania jej za pomocą def.
# Funkcje lambda są przydatne do zwięzłego i czytelnego kodu, szczególnie w kontekstach programowania funkcyjnego.