# Zaimplementować własne wyjątki do obsługi błędów odczytu i zapisu wyświetlające komunikaty
# o błędach w sposób spersonalizowany.

import classes

# Defining custom exceptions for read and write errors
class ReadError(Exception):
    def __init__(self, message):
        super().__init__(f"Read Error: {message}")


class WriteError(Exception):
    def __init__(self, message):
        super().__init__(f"Write Error: {message}")

# Extending the Group classes to use custom exceptions
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

    def save_to_file(self, file_path):
        try:
            with open(file_path, 'w') as file:
                for student in self.students:
                    file.write(f"{student.get_name()}, {student.get_last_name()}, {student.get_age()}, {student.get_index_nr()}\n")
            print(f"Group successfully saved to {file_path}")
        except IOError as e:
            raise WriteError(f"An error occurred while saving the group to the file: {e}")

    def load_from_file(self, file_path):
        try:
            with open(file_path, 'r') as file:
                for line in file:
                    try:
                        name, last_name, age, index_nr = line.strip().split(", ")
                        self.add_student(classes.student.Student(name, last_name, int(age), index_nr))
                    except ValueError as e:
                        raise ReadError(f"Error parsing line: {line.strip()} - {e}")
            print(f"Group successfully loaded from {file_path}")
        except IOError as e:
            raise ReadError(f"An error occurred while reading the file: {e}")


# Testing the custom exceptions
group = Group()

try:
    group.load_from_file("nonexistent_file.txt")  # Simulated read error
except ReadError as e:
    print(e)

print()  # Empty line for better readability

student1 = classes.student.Student("Alice", "Brown", 20, "S12345")
student2 = classes.student.Student("Bob", "Green", 21, "S67890")

group.add_student(student1)
group.add_student(student2)

try:
    group.save_to_file("/invalid_path/zad10_out.txt")  # Simulated write error
except WriteError as e:
    print(e)
