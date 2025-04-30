# Zaimplementować mechanizm odczytu grupy studentów z pliku. Obsłużyć wyjatki związane
# z odczytem danych. Zasymulować błąd odczytu (np. poprzez zablokowanie do zapisu)
# i sprawdzić działanie mechanizmu wyjątków.

import classes

# Extending the Group classes to include file reading functionality
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

    def load_from_file(self, file_path):
        try:
            with open(file_path, 'r') as file:
                for line in file:
                    try:
                        name, last_name, age, index_nr = line.strip().split(", ")
                        self.add_student(classes.student.Student(name, last_name, int(age), index_nr))
                    except ValueError as e:
                        print(f"Error parsing line: {line.strip()} - {e}")
            print(f"Group successfully loaded from {file_path}")
        except IOError as e:
            print(f"An error occurred while reading the file: {e}")


# Example usage
# Simulating a read error by providing an invalid file path
group = Group()
try:
    group.load_from_file("nonexistent_file.txt") # Simulated error
except Exception as e:
    print(f"Simulated error: {e}")

print() # Empty line for better readability

# Assuming a valid file `students_group.txt` exists with the following content:
# Alice, Brown, 20, S12345
# Bob, Green, 21, S67890
group.load_from_file("zad08_out.txt")
group.list_students()
