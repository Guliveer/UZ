# Zdefiniować klasę Wektor zawierającą współrzędne wektora w dwóch wymiarach (na płaszczyźnie).
# Przeładować operatory arytmetyczne (dodawanie, odejmowanie, mnożenie, produkt
# Hadamarda). Przeładować operator konwersji do łańcucha znaków tak aby wywołanie
# z Listingu 9 zwracało współrzędne wektora. Zadanie wykonać bez importowania dodatkowych modułów.
#
# Listing 9:
# w = Wektor(3, 4)
# print(w)

# Klasa Wektor
class Wektor:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    # Przeładowanie operatora dodawania
    def __add__(self, other):
        return Wektor(self.x + other.x, self.y + other.y)

    # Przeładowanie operatora odejmowania
    def __sub__(self, other):
        return Wektor(self.x - other.x, self.y - other.y)

    # Przeładowanie operatora mnożenia (iloczyn skalarny)
    def __mul__(self, other):
        return self.x * other.x + self.y * other.y

    # Przeładowanie operatora iloczynu Hadamarda
    def hadamard(self, other):
        return Wektor(self.x * other.x, self.y * other.y)

    # Przeładowanie operatora konwersji do łańcucha znaków
    def __str__(self):
        return f"Wektor({self.x}, {self.y})"


# Testowanie klasy Wektor
w1 = Wektor(3, 4)
w2 = Wektor(1, 2)

# Dodawanie
w3 = w1 + w2
print("Dodawanie:", w3)  # Wektor(4, 6)

# Odejmowanie
w4 = w1 - w2
print("Odejmowanie:", w4)  # Wektor(2, 2)

# Iloczyn skalarny
dot_product = w1 * w2
print("Iloczyn skalarny:", dot_product)  # 11

# Iloczyn Hadamarda
w5 = w1.hadamard(w2)
print("Iloczyn Hadamarda:", w5)  # Wektor(3, 8)