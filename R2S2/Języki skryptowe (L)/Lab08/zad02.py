# Transponować macierze z zadania 1.

import numpy as np

# Macierze z zadania 1
A = np.array([[1, 2], [4, 5], [7, 8]])
B = np.array([[2, 0, 0], [0, 2, 0], [0, 0, 2]])
C = np.array([2, 1, 0])
D = np.array([[3, 2, 1], [0, 5, 6], [8, -1, 2]])

# Transpozycje
print("A.T:\n", A.T, "\n")
print("B.T:\n", B.T, "\n")
print("C.T:\n", C.T, "\n")
print("D.T:\n", D.T, "\n")
