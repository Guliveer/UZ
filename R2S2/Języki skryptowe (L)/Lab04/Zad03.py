# Zmodyfikować funkcje z zad. 2. tak aby wspierały operacje na macierzach.

def elementwise_operation(a, b, op):
    if isinstance(a[0], list) and isinstance(b[0], list):
        if len(a) != len(b) or len(a[0]) != len(b[0]):
            raise ValueError("Matrices must have the same dimensions for addition or subtraction.")
        return [[op(x, y) for x, y in zip(row_a, row_b)] for row_a, row_b in zip(a, b)]
    return [op(x, y) for x, y in zip(a, b)]

def dodaj(a, b):
    return elementwise_operation(a, b, lambda x, y: x + y)

def odejmij(a, b):
    return elementwise_operation(a, b, lambda x, y: x - y)

def pomnoz(a, b):
    if isinstance(a[0], list) and isinstance(b[0], list):
        if len(a[0]) != len(b):
            raise ValueError("Number of columns in the first matrix must equal the number of rows in the second matrix for multiplication.")
        return [[sum(x * y for x, y in zip(row_a, col_b)) for col_b in zip(*b)] for row_a in a]
    return a * b

def print_matrix(matrix):
    for row in matrix:
        print(" ".join(map(str, row)))

def __main__():
    print("Wybierz operację:")
    print("1. Dodaj")
    print("2. Odejmij")
    print("3. Pomnoz")

    wybor = input("Wybierz 1, 2 lub 3: ")

    if wybor in ['1', '2', '3']:
        typ = input("Czy operacja ma być wykonana na liczbach (l), wektorach (w) czy macierzach (m)? ")

        if typ == 'l':
            a = float(input("Podaj pierwsza liczbe: "))
            b = float(input("Podaj druga liczbe: "))
        elif typ == 'w':
            a = list(map(float, input("Podaj elementy pierwszego wektora oddzielone spacjami: ").split()))
            b = list(map(float, input("Podaj elementy drugiego wektora oddzielone spacjami: ").split()))
        elif typ == 'm':
            a = [list(map(float, input(f"Podaj elementy {i+1} wiersza pierwszej macierzy oddzielone spacjami: ").split())) for i in range(int(input("Podaj liczbe wierszy pierwszej macierzy: ")))]
            b = [list(map(float, input(f"Podaj elementy {i+1} wiersza drugiej macierzy oddzielone spacjami: ").split())) for i in range(int(input("Podaj liczbe wierszy drugiej macierzy: ")))]
        else:
            print("Niepoprawny wybór.")
            return

        if wybor == '1':
            wynik = dodaj(a, b)
            print("Wynik dodawania:")
        elif wybor == '2':
            wynik = odejmij(a, b)
            print("Wynik odejmowania:")
        elif wybor == '3':
            wynik = pomnoz(a, b)
            print("Wynik mnozenia:")

        if typ == 'm':
            print_matrix(wynik)
        else:
            print(wynik)
    else:
        print("Niepoprawny wybór.")

__main__()
