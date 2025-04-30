# Zaimplementować mechanizm zapisu grupy studentów do pliku.
# Obsłużyć wyjątki związane z zapisem danych.

# Defining the Person class
class Person:
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

# Defining the Student class inheriting from Person
class Student(Person):
    def __init__(self, name, last_name, age, index_nr):
        super().__init__(name, last_name, age)
        self.index_nr = index_nr

    def get_index_nr(self):
        return self.index_nr

# Extending the Group class to include file saving functionality
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
            print(f"An error occurred while saving the group to the file: {e}")


# Example usage
student1 = Student("Alice", "Brown", 20, "S12345")
student2 = Student("Bob", "Green", 21, "S67890")

group = Group()
group.add_student(student1)
group.add_student(student2)

# Save the group to a file
group.save_to_file("zad08_out.txt")
