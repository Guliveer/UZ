# Utworzyć klasę Employee dziedziczącą po Person. Zainicjalizować obiekt tej klasy o dowolnych
# atrybutach salary, position.

# Defining the Osoba class
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


# Example usage
employee = Employee("Michael", "Scott", 45, 5000, "Manager")
print(employee.get_name())       # Output: Michael
print(employee.get_last_name())  # Output: Scott
print(employee.get_age())        # Output: 45
print(employee.get_salary())     # Output: 5000
print(employee.get_position())   # Output: Manager
