import person

# Defining the Student classes inheriting from Person
class Student(person.Person):
    def __init__(self, name, last_name, age, index_nr):
        super().__init__(name, last_name, age)
        self.index_nr = index_nr

    def get_index_nr(self):
        return self.index_nr
