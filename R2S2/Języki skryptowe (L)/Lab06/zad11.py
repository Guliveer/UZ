# Zdefiniować zmienną chronioną 'id' klasy Osoba oraz zmienną prywatną 'wiek'.
# Sprawdzić poleceniem dir() w jaki sposób zostały utworzone zmienne w strukturze klasy.

class Osoba:
    def __init__(self, imie="Jan", nazwisko="Kowalski", id=0, wiek=30):
        self.imie = imie
        self.nazwisko = nazwisko
        self._id = id  # Zmienna chroniona
        self.__wiek = wiek  # Zmienna prywatna

    def przywitaj(self):
        print("Witaj, " + self.imie + " " + self.nazwisko)


# Tworzenie obiektu klasy Osoba
osoba = Osoba("Anna", "Nowak", id=123, wiek=25)

# Wyświetlenie struktury klasy za pomocą dir()
print(dir(osoba))
