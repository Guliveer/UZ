# Jaka jest różnica pomiędzy break a continue?

# Przykład użycia break
print("Break:")
for i in range(10):
    if i == 5:
        break # Przerywa pętlę
    print(i)
# Wynik: 0 1 2 3 4

print()

# Przykład użycia continue
print("Continue:")
for i in range(10):
    if i == 5:
        continue # Pomija resztę pętli i przechodzi do następnej iteracji
    print(i)
# Wynik: 0 1 2 3 4 6 7 8 9