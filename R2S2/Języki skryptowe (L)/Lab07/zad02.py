# W jaki sposób Python rozwiązuje „problemu diamentowego” w dziedziczeniu wielobazowym?

class A:
    def method(self):
        print("Method from classes A")

class B(A):
    def method(self):
        print("Method from classes B")

class C(A):
    def method(self):
        print("Method from classes C")

class D(B, C):  # Diamond inheritance
    pass

# Creating an object of classes D
obj = D()
obj.method()  # Resolves using MRO

# Checking the MRO
print(D.__mro__)
