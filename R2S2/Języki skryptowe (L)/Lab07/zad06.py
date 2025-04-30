# Zaimplementować klasę WorkingStudent dziedziczącą po Student i Employee. Zainicjaliować
# dowolnymi danymi.

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

# Defining the Employee class inheriting from Person
class Employee(Person):
    def __init__(self, name, last_name, age, salary, position):
        super().__init__(name, last_name, age)
        self.salary = salary
        self.position = position

    def get_salary(self):
        return self.salary

    def get_position(self):
        return self.position

# Defining the Student class inheriting from Person
class Student(Person):
    def __init__(self, name, last_name, age, index_nr):
        super().__init__(name, last_name, age)
        self.index_nr = index_nr

    def get_index_nr(self):
        return self.index_nr

# Defining the WorkingStudent class inheriting from Student and Employee
class WorkingStudent(Student, Employee):
    def __init__(self, name, last_name, age, index_nr, salary, position):
        # Initialize Person attributes using super()
        Person.__init__(self, name, last_name, age)
        # Initialize Student-specific attributes
        self.index_nr = index_nr
        # Initialize Employee-specific attributes
        self.salary = salary
        self.position = position

# Example usage
working_student = WorkingStudent("Anna", "Kowalska", 25, "S98765", 3000, "Intern")
print(working_student.get_name())       # Output: Anna
print(working_student.get_last_name())  # Output: Kowalska
print(working_student.get_age())        # Output: 25
print(working_student.get_index_nr())   # Output: S98765
print(working_student.get_salary())     # Output: 3000
print(working_student.get_position())   # Output: Intern
