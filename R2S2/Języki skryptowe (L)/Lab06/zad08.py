# Wykorzystać wyrażenie lambda oraz funkcję map do stworzenia
# listy pierwszych dziesięciu naturalnych liczb parzystych.

even_numbers = list(map(lambda x: x * 2, range(0, 10)))

print(even_numbers)
