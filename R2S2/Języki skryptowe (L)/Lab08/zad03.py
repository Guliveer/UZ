# Policzyć ślad macierzy z zadania 1. używając funkcji trace.

import numpy as np

# Macierze
A = np.array([[1, 2], [4, 5], [7, 8]])        # (3x2) → niekwadratowa
B = np.array([[2, 0, 0], [0, 2, 0], [0, 0, 2]])  # (3x3)
C = np.array([2, 1, 0])                        # wektor 1D
D = np.array([[3, 2, 1], [0, 5, 6], [8, -1, 2]]) # (3x3)

# Ślad obliczamy tylko dla macierzy kwadratowych
def try_trace(matrix, name):
    if matrix.ndim == 2 and matrix.shape[0] == matrix.shape[1]:
        print(f"Ślad macierzy {name}: {np.trace(matrix)}")
    else:
        print(f"Macierz {name} nie jest kwadratowa – ślad nie istnieje.")

try_trace(A, "A")
try_trace(B, "B")
try_trace(C, "C")
try_trace(D, "D")
