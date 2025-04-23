# Czym różni się funkcja (tworzona przez def) od wyrażenia lambda?

# def
def add_numbers(a, b):
    return a + b

# lambda
add_lambda = lambda a, b: a + b

print(add_numbers(3, 5))  # Wynik: 8
print(add_lambda(3, 5))   # Wynik: 8