# W jaki sposób Python unika "problemu diamentowego" w dziedziczeniu wielokrotnym (wielobazowym)?

class A:
    def greet(self):
        return "Hello from A"

class B(A):
    def greet(self):
        return "Hello from B"

class C(A):
    def greet(self):
        return "Hello from C"

class D(B, C):  # Wielobazowe dziedziczenie
    pass

d = D()
print(d.greet())  # Wynik: Hello from B
print(D.mro())    # Pokazuje metodę rozwiązywania porządku (MRO); Wynik: D, B, C, A