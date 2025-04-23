# Utworzyć klasę Osoba o polach imie i nazwisko. Utworzyć klasy potomne Student oraz Pracownik,
# zawierające odpowiednio pola nr ind, oceny oraz stanowisko, wynagrodzenie. Stosując
# mechanizm dziedziczenia utworzyć klasę PracujacyStudent oraz instancję tej klasy w postaci
# obiektu o nazwie WS.

class Osoba:
    def __init__(self, imie="Jan", nazwisko="Kowalski"):
        self.imie = imie
        self.nazwisko = nazwisko

    def przywitaj(self):
        print("Witaj, " + self.imie + " " + self.nazwisko)

# Klasa Student dziedzicząca po klasie Osoba
class Student(Osoba):
    def __init__(self, imie="Jan", nazwisko="Kowalski", nr_ind=0, oceny=None):
        super().__init__(imie, nazwisko)
        self.nr_ind = nr_ind  # Numer indeksu
        self.oceny = oceny if oceny is not None else []  # Lista ocen

    def pokaz_oceny(self):
        print(f"Oceny studenta {self.imie} {self.nazwisko}: {self.oceny}")


# Klasa Pracownik dziedzicząca po klasie Osoba
class Pracownik(Osoba):
    def __init__(self, imie="Jan", nazwisko="Kowalski", stanowisko="Nieznane", wynagrodzenie=0):
        super().__init__(imie, nazwisko)
        self.stanowisko = stanowisko  # Stanowisko pracy
        self.wynagrodzenie = wynagrodzenie  # Wynagrodzenie

    def pokaz_informacje_pracownika(self):
        print(f"Pracownik {self.imie} {self.nazwisko}, stanowisko: {self.stanowisko}, wynagrodzenie: {self.wynagrodzenie} zł")


# Klasa PracujacyStudent dziedzicząca po klasach Student i Pracownik
class PracujacyStudent(Student, Pracownik):
    def __init__(self, imie="Jan", nazwisko="Kowalski", nr_ind=0, oceny=None, stanowisko="Nieznane", wynagrodzenie=0):
        Student.__init__(self, imie, nazwisko, nr_ind, oceny)
        Pracownik.__init__(self, imie, nazwisko, stanowisko, wynagrodzenie)

    def pokaz_informacje(self):
        print(f"Pracujący student {self.imie} {self.nazwisko}:")
        print(f"\tNumer indeksu: {self.nr_ind}")
        print(f"\tOceny: {self.oceny}")
        print(f"\tStanowisko: {self.stanowisko}")
        print(f"\tWynagrodzenie: {self.wynagrodzenie} zł")


# Tworzenie obiektu klasy PracujacyStudent
WS = PracujacyStudent(imie="Anna", nazwisko="Nowak", nr_ind=12345, oceny=[5, 4, 3, 5], stanowisko="Asystent", wynagrodzenie=3000)

# Wyświetlenie informacji o obiekcie WS
WS.pokaz_informacje()