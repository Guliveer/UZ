# Sprawdzić wartość stałej π oraz sin(π) z użyciem numpy

import numpy as np

pi = np.pi
sin_pi = np.sin(pi)

print(f"π = {pi}")
print(f"sin(π) = {sin_pi}")

# Sprawdzenie, czy wynik jest dokładnie równy 0
print(f"sin(π) == 0: {sin_pi == 0}")
