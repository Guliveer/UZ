# Zaimplementować klasę Group zawierającą listę studentów.
# Zainicjalizować obiekt z dowolnymi studentami.

import classes

# Defining the Group classes
class Group:
    def __init__(self):
        self.students = []

    def add_student(self, student):
        self.students.append(student)

    def remove_student(self, student):
        if student in self.students:
            self.students.remove(student)

    def list_students(self):
        for student in self.students:
            print(f"Name: {student.get_name()}, Last Name: {student.get_last_name()}, Age: {student.get_age()}, Index: {student.get_index_nr()}")


# Example usage
student1 = classes.student.Student("Alice", "Brown", 20, "S12345")
student2 = classes.student.Student("Bob", "Green", 21, "S67890")

group = Group()
group.add_student(student1)
group.add_student(student2)

# Listing students in the group
group.list_students()
print() # Empty line for better readability

# Removing a student and listing again
group.remove_student(student1)
group.list_students()
