# Zdefiniować klasę Punkt o polach x,y oraz metodzie wyświetlającej pozycję punktu
# (np. "Punkt znajduje się w (1,4)"). Następnie utworzyć klasę Odcinek która będzie delegowała
# do klasy punkt (pozycje punktu początkowego i końcowego). Utworzyć w niej metodę wyświetlającą
# długość odcinka. Następnie zdefiniować klasę Trójkąt która będzie zawierała 3 Punkty,
# automatycznie wyznaczała z nich 3 odcinki (ściany) oraz zawierała metodę do wyświetlania pola
# powierzchni o obwodu. Czy w takim przypadku lepiej zastosować dziedziczenie czy delegację?

class Punkt:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def pokaz_pozycje(self):
        print(f"Punkt znajduje się w ({self.x}, {self.y})")

class Odcinek:
    def __init__(self, p1: Punkt, p2: Punkt):
        self.p1 = p1
        self.p2 = p2

    def dlugosc(self):
        return ((self.p1.x - self.p2.x)**2 + (self.p1.y - self.p2.y)**2)**0.5

class Trojkat:
    def __init__(self, a: Punkt, b: Punkt, c: Punkt):
        self.a = a
        self.b = b
        self.c = c
        self.ab = Odcinek(a, b)
        self.bc = Odcinek(b, c)
        self.ca = Odcinek(c, a)

    def obwod(self):
        return self.ab.dlugosc() + self.bc.dlugosc() + self.ca.dlugosc()

    def pole(self):
        # Wzór Herona
        a = self.ab.dlugosc()
        b = self.bc.dlugosc()
        c = self.ca.dlugosc()
        s = (a + b + c) / 2
        return (s * (s - a) * (s - b) * (s - c))**0.5

    def pokaz_info(self):
        print(f"Obwód trójkąta: {self.obwod():.2f}")
        print(f"Pole trójkąta: {self.pole():.2f}")

# Tworzenie punktów
p1 = Punkt(0, 0)
p1.pokaz_pozycje()

p2 = Punkt(4, 0)
p2.pokaz_pozycje()

p3 = Punkt(0, 3)
p3.pokaz_pozycje()

# Tworzenie trójkąta
trojkat = Trojkat(p1, p2, p3)
trojkat.pokaz_info()