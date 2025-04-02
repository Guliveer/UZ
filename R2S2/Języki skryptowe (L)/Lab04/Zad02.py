# Zmodyfikować funkcje z zad. 1. tak aby wspierały wykonywanie operacji arytmetycznych na
# wektorach (w postaci list).

def dodaj(a, b):
    if isinstance(a, list) and isinstance(b, list):
        return [x + y for x, y in zip(a, b)]
    return a + b

def odejmij(a, b):
    if isinstance(a, list) and isinstance(b, list):
        return [x - y for x, y in zip(a, b)]
    return a - b

def pomnoz(a, b):
    if isinstance(a, list) and isinstance(b, list):
        return [x * y for x, y in zip(a, b)]
    return a * b

def __main__():
    print("Wybierz operację:")
    print("1. Dodaj")
    print("2. Odejmij")
    print("3. Pomnoz")

    wybor = input("Wybierz 1, 2 lub 3: ")

    if wybor in ['1', '2', '3']:
        typ = input("Czy operacja ma być wykonana na liczbach (l) czy wektorach (w)? ")

        if typ == 'l':
            a = float(input("Podaj pierwsza liczbe: "))
            b = float(input("Podaj druga liczbe: "))
        elif typ == 'w':
            a = list(map(float, input("Podaj elementy pierwszego wektora oddzielone spacjami: ").split()))
            b = list(map(float, input("Podaj elementy drugiego wektora oddzielone spacjami: ").split()))
        else:
            print("Niepoprawny wybór.")
            return

        if wybor == '1':
            print(f"Wynik dodawania {a} + {b} = {dodaj(a, b)}")
        elif wybor == '2':
            print(f"Wynik odejmowania {a} - {b} = {odejmij(a, b)}")
        elif wybor == '3':
            print(f"Wynik mnozenia {a} * {b} = {pomnoz(a, b)}")
    else:
        print("Niepoprawny wybór.")

__main__()
