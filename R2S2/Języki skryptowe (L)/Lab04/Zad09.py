# Korzystając z mechanizmu przeładowania modułów napisać program pozwalający
# na modyfikację modułu myalgebra pomiędzy kolejnymi wywołaniami polecenia input() w pętli, które
# przyjmuje kolejne elementy wektora aż do wpisania litery K.

# Zad09.py

import importlib
from mymath import myalgebra

def read_vector():
    vector = []
    while True:
        element = input("Podaj element (lub 'K' aby zakończyć): ")
        if element.upper() == 'K':
            break
        try:
            vector.append(float(element))
        except ValueError:
            print("Niepoprawny element, spróbuj ponownie.")
    return vector

def main():
    while True:
        print("Wybierz operację:")
        print("1. Dodaj")
        print("2. Odejmij")
        print("3. Pomnoz")
        print("4. Przeładuj moduł myalgebra")

        wybor = input("Wybierz 1, 2, 3 lub 4: ")

        if wybor in ['1', '2', '3']:
            print("Elementy pierwszego wektora:")
            a = read_vector()
            print("Elementy drugiego wektora:")
            b = read_vector()

            if wybor == '1':
                wynik = myalgebra.dodaj(a, b)
                print("\nWynik dodawania:", wynik)
            elif wybor == '2':
                wynik = myalgebra.odejmij(a, b)
                print("\nWynik odejmowania:", wynik)
            elif wybor == '3':
                wynik = myalgebra.pomnoz(a, b)
                print("\nWynik mnożenia:", wynik)
        elif wybor == '4':
            importlib.reload(myalgebra)
            print("Moduł myalgebra został przeładowany.")
        else:
            print("Niepoprawny wybór.")

if __name__ == "__main__":
    main()