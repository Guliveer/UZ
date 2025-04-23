# Co to jest polimorfizm?

class Animal:
    def make_sound(self):
        pass # Ta metoda będzie nadpisana w klasach dziedziczących

class Dog(Animal):
    def make_sound(self):
        return "Woof!"

class Cat(Animal):
    def make_sound(self):
        return "Meow!"

# Użycie polimorfizmu
def animal_sound(animal):
    print(animal.make_sound())

dog = Dog()
cat = Cat()

animal_sound(dog)  # Wynik: Woof!
animal_sound(cat)  # Wynik: Meow!