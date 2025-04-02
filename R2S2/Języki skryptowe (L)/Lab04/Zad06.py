# Do pakietu z zadania 5. dodać pakiety z funkcjami z zadań 1 i 2

from mymath import *

def read_input(typ):
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
        raise ValueError("Niepoprawny wybór.")
    return a, b

def __main__():
    print("Wybierz operację:")
    print("1. Dodaj")
    print("2. Odejmij")
    print("3. Pomnoz")

    wybor = input("Wybierz 1, 2 lub 3: ")

    if wybor in ['1', '2', '3']:
        typ = input("Czy operacja ma być wykonana na liczbach (l), wektorach (w) czy macierzach (m)? ")

        try:
            a, b = read_input(typ)
        except ValueError as e:
            print(e)
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