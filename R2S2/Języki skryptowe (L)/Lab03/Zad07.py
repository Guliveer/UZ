# Utworzyć tensor o wymiarach 3 × 3 × 3 ilorazów indeksów tensora, analogicznie jak w Zadaniu 6.

tensor = [[[(i + 1) / (j + 1) / (k + 1) for k in range(3)] for j in range(3)] for i in range(3)]

for layer in tensor:
    for row in layer:
        print('\t'.join(map(str, row)))
    print()
