# Defining the Person classes
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
