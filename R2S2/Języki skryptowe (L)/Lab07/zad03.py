# Utworzyć klasę Osoba o atrybutach name, last name, age oraz obiekt o tej klasy o dowolnych
# wartościach parametrów. Zaimplementować metody getName, getLastName, getAge. Utworzyć
# klasę potomną Student dziedziczącą po Person o atrybucie indexNr i metodzie getIndexNr

# Defining the Osoba class
class Osoba:
    def __init__(self, name, last_name, age):
        self.name = name
        self.last_name = last_name
        self.age = age

    def get_name(self):
        return self.name

    def get_last_name(self):
        return self.last_name

    def get_age(self):
        return self.age


# Defining the Student class inheriting from Osoba
class Student(Osoba):
    def __init__(self, name, last_name, age, index_nr):
        super().__init__(name, last_name, age)
        self.indexNr = index_nr

    def get_index_nr(self):
        return self.indexNr


# Example usage
osoba = Osoba("John", "Doe", 30)
print(osoba.get_name())       # Output: John
print(osoba.get_last_name())   # Output: Doe
print(osoba.get_age())        # Output: 30

student = Student("Jane", "Smith", 22, "S12345")
print(student.get_name())     # Output: Jane
print(student.get_last_name()) # Output: Smith
print(student.get_age())      # Output: 22
print(student.get_index_nr())  # Output: S12345
