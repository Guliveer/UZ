# W jaki sposób realizowane są referencje do metod dziedziczonych z klas nadrzędnych w obiekcie
# dziedziczącym z wielu klas bazowych?

class A:
    def method(self):
        print("Method from classes A")

class B:
    def method(self):
        print("Method from classes B")

class C(A, B):  # Dziedziczenie wielokrotne
    pass

# Tworzenie obiektu klasy C
obj = C()
obj.method()  # Wywołanie metody

# Sprawdzenie kolejności MRO
print(C.__mro__)  # lub C.mro()
