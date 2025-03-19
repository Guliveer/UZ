# Utworzyć listę słowników zawierającą: imiona nazwiska, wiek, oraz 10 ocen studentów.
# Wykorzystując mechanizm list składanych obliczyć średnią ocen każdego studenta, średnią ocen
# wszystkich studentów oraz medianę wieku oraz medianę długości nazwiska.

import statistics

# Utwórz listę słowników zawierającą dane studentów
students = [
    {"name": "John", "surname": "Doe", "age": 20, "grades": [2, 3, 4, 5, 3, 4, 5, 2, 3, 4]},
    {"name": "Jane", "surname": "Smith", "age": 22, "grades": [5, 3, 2, 4, 5, 3, 4, 5, 2, 3]},
    {"name": "Alice", "surname": "Johnson", "age": 21, "grades": [4, 2, 5, 5, 3, 4, 5, 2, 3, 4]},
    {"name": "Bob", "surname": "Brown", "age": 23, "grades": [3, 3, 2, 4, 5, 3, 4, 5, 2, 3]},
    {"name": "Eve", "surname": "Williams", "age": 24, "grades": [5, 3, 4, 5, 3, 4, 5, 2, 3, 4]}
]

# Oblicz średnią ocen każdego studenta
avg_grades = [sum(student["grades"]) / len(student["grades"]) for student in students]

# Oblicz średnią ocen wszystkich studentów
total_grades = sum(sum(student["grades"]) for student in students)
total_count = sum(len(student["grades"]) for student in students)
avg_all_students = total_grades / total_count

# Oblicz medianę wieku studentów
ages = [student["age"] for student in students]
median_age = statistics.median(ages)

# Oblicz medianę długości nazwisk studentów
surname_lengths = [len(student["surname"]) for student in students]
median_surname_length = statistics.median(surname_lengths)

print("Średnia ocen każdego studenta:", avg_grades)
print("Średnia ocen wszystkich studentów:", avg_all_students)
print("Mediana wieku studentów:", median_age)
print("Mediana długości nazwisk:", median_surname_length)