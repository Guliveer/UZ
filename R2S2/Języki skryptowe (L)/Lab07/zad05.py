# Utworzyć klasę Employee dziedziczącą po Person. Zainicjalizować obiekt tej klasy o dowolnych
# atrybutach salary, position.

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


# Example usage
employee = Employee("Michael", "Scott", 45, 5000, "Manager")
print(employee.get_name())       # Output: Michael
print(employee.get_last_name())  # Output: Scott
print(employee.get_age())        # Output: 45
print(employee.get_salary())     # Output: 5000
print(employee.get_position())   # Output: Manager
