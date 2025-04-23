# Zdefiniować klasę Prostokąt o polach x, y, w, h przechowujących kolejno położenie lewego
# górnego rogu w układzie współrzędnych na osiach x i y oraz szerokość i wysokość prostokąta.
# Zdefiniować klasę Okno zawierającą nazwę, kolor. Utworzyć obiekt Okno1. Położenie oraz
# rozmiar okna delegować do obiektu Prostokąt.

# Klasa Prostokat
class Prostokat:
    def __init__(self, x, y, w, h):
        self.x = x  # Położenie lewego górnego rogu na osi x
        self.y = y  # Położenie lewego górnego rogu na osi y
        self.w = w  # Szerokość prostokąta
        self.h = h  # Wysokość prostokąta

    def pokaz_pozycje_i_rozmiar(self):
        print(f"Prostokąt: położenie ({self.x}, {self.y}), szerokość {self.w}, wysokość {self.h}")


# Klasa Okno
class Okno:
    def __init__(self, nazwa, kolor, prostokat: Prostokat):
        self.nazwa = nazwa  # Nazwa okna
        self.kolor = kolor  # Kolor okna
        self.prostokat = prostokat  # Delegacja do obiektu Prostokat

    def pokaz_informacje(self):
        print(f"Okno: {self.nazwa}, kolor: {self.kolor}")
        self.prostokat.pokaz_pozycje_i_rozmiar()


# Tworzenie obiektu Prostokat
prostokat = Prostokat(10, 20, 200, 150)

# Tworzenie obiektu Okno
okno1 = Okno("Okno1", "Niebieski", prostokat)

# Wyświetlenie informacji o oknie
okno1.pokaz_informacje()
