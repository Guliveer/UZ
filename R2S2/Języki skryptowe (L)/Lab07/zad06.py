# Zaimplementować klasę WorkingStudent dziedziczącą po Student i Employee. Zainicjaliować
# dowolnymi danymi.

import classes

# Defining the Employee classes inheriting from Person
class Employee(classes.person.Person):
    def __init__(self, name, last_name, age, salary, position):
        super().__init__(name, last_name, age)
        self.salary = salary
        self.position = position

    def get_salary(self):
        return self.salary

    def get_position(self):
        return self.position

# Defining the WorkingStudent classes inheriting from Student and Employee
class WorkingStudent(classes.student.Student, Employee):
    def __init__(self, name, last_name, age, index_nr, salary, position):
        # Initialize Person attributes using super()
        classes.person.Person.__init__(self, name, last_name, age)
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
