# Utworzyć funkcje dodaj, odejmij, pomnoz które będą wykonywane zadane operacje arytmetyczne,
# odpowiednio +,-,*.

def dodaj(a, b):
    return a + b

def odejmij(a, b):
    return a - b

def pomnoz(a, b):
    return a * b

def __main__():
    print("Wybierz operację:")
    print("1. Dodaj")
    print("2. Odejmij")
    print("3. Pomnoz")

    wybor = input("Wybierz 1, 2 lub 3: ")

    if wybor in ['1', '2', '3']:
        a = float(input("Podaj pierwsza liczbe: "))
        b = float(input("Podaj druga liczbe: "))

        if wybor == '1':
            print(f"Wynik dodawania {a} + {b} = {dodaj(a, b)}")
        elif wybor == '2':
            print(f"Wynik odejmowania {a} - {b} = {odejmij(a, b)}")
        elif wybor == '3':
            print(f"Wynik mnozenia {a} * {b} = {pomnoz(a, b)}")
    else:
        print("Niepoprawny wybór.")

__main__()
