# Utworzyć klasę Notes zawierającą atrybut w postaci słownika ocen (przedmiot: ocena). Dodać
# atrybut notes to klasy Student. Zainicjalizować dwóch studentów z dowolnymi ocenami z więcej
# niż trzech przedmiotów.

import classes

# Defining the Notes classes
class Notes:
    def __init__(self, grades):
        self.grades = grades

    def get_grades(self):
        return self.grades

    def add_grade(self, subject, grade):
        self.grades[subject] = grade

    def get_average(self):
        return sum(self.grades.values()) / len(self.grades)

# Updating the Student classes to include the notes attribute
class Student(classes.person.Person):
    def __init__(self, name, last_name, age, index_nr, grades):
        super().__init__(name, last_name, age)
        self.indexNr = index_nr
        self.notes = Notes(grades)

    def get_index_nr(self):
        return self.indexNr

    def get_notes(self):
        return self.notes


# Example usage
student1 = Student("Alice", "Brown", 20, "S12345", {"Math": 4.5, "Physics": 5.0, "History": 4.0, "Biology": 3.5})
student2 = Student("Bob", "Green", 21, "S67890", {"Math": 3.5, "Physics": 4.0, "History": 4.5, "Chemistry": 5.0})

# Accessing student data
print(student1.get_name())  # Output: Alice
print(student1.get_notes().get_grades())  # Output: {'Math': 4.5, 'Physics': 5.0, 'History': 4.0, 'Biology': 3.5}
print(student1.get_notes().get_average()) # Output: 4.25

print(student2.get_name())  # Output: Bob
print(student2.get_notes().get_grades())  # Output: {'Math': 3.5, 'Physics': 4.0, 'History': 4.5, 'Chemistry': 5.0}
print(student2.get_notes().get_average()) # Output: 4.25
