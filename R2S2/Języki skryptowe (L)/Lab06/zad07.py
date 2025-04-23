# Wykorzystując polecenie 'type' sprawdzić typ obiektu zwracanego przez funkcję (print)
# oraz metodę przywitaj klasy Osoba.

class Osoba:
    def __init__(self, imie="Jan", nazwisko="Kowalski"):
        self.imie = imie
        self.nazwisko = nazwisko

    def przywitaj(self):
        print("Witaj, " + self.imie + " " + self.nazwisko)

osoba = Osoba("Anna", "Nowak")
print(type(osoba.przywitaj()))
print(type(print("Test")))