# Utworzyć macierz o wymiarach 4 × 4 iloczynów indeksów macierzy.
#
#  a1,1 a1,2 a1,3 a1,4 
# | a2,1 a2,2 a2,3 a2,4 |
# | a3,1 a3,2 a3,3 a3,4 |, ∀ai,j = ij
#  a4,1 a4,2 a4,3 a4,4 

matrix = [[(i + 1) * (j + 1) for j in range(4)] for i in range(4)]

for row in matrix:
    print('\t'.join(map(str, row))) # Wynik z tabami
    # print(row) # Surowy wynik (bez tabów)