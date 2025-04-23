# Wykonać polecenie z zad12. wykorzystując mechanizm dziedziczenia.

# Klasa Prostokat
class Prostokat:
    def __init__(self, x, y, w, h):
        self.x = x  # Położenie lewego górnego rogu na osi x
        self.y = y  # Położenie lewego górnego rogu na osi y
        self.w = w  # Szerokość prostokąta
        self.h = h  # Wysokość prostokąta

    def pokaz_pozycje_i_rozmiar(self):
        print(f"Prostokąt: położenie ({self.x}, {self.y}), szerokość {self.w}, wysokość {self.h}")


# Klasa Okno dziedzicząca po klasie Prostokat
class Okno(Prostokat):
    def __init__(self, x, y, w, h, nazwa, kolor):
        super().__init__(x, y, w, h)  # Inicjalizacja pól klasy bazowej
        self.nazwa = nazwa  # Nazwa okna
        self.kolor = kolor  # Kolor okna

    def pokaz_informacje(self):
        print(f"Okno: {self.nazwa}, kolor: {self.kolor}")
        self.pokaz_pozycje_i_rozmiar()


# Tworzenie obiektu Okno
okno1 = Okno(10, 20, 200, 150, "Okno1", "Niebieski")

# Wyświetlenie informacji o oknie
okno1.pokaz_informacje()
