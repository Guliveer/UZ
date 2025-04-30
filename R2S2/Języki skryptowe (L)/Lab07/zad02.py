# W jaki sposób Python rozwiązuje „problemu diamentowego” w dziedziczeniu wielobazowym?

class A:
    def method(self):
        print("Method from class A")

class B(A):
    def method(self):
        print("Method from class B")

class C(A):
    def method(self):
        print("Method from class C")

class D(B, C):  # Diamond inheritance
    pass

# Creating an object of class D
obj = D()
obj.method()  # Resolves using MRO

# Checking the MRO
print(D.__mro__)
