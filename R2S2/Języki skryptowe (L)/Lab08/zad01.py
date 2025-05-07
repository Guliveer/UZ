# Wprowadzić i wyświetlić następujące macierze:
# A = [[1, 2], [4, 5], [7, 8]]
# B = [[2, 0, 0], [0, 2, 0], [0, 0, 2]]
# C = [2, 1, 0]
# D = [[3, 2, 1], [0, 5, 6], [8, -1, 2]]
#
# Wykonać następujące działania sprawdzając uprzednio czy to możliwe za pomocą funkcji shape:
# B+D, 3*A, −2*C, B*A, D*B, 2*A+B−C, C*D − D*C, 2*B − D, D*D, B*B + D*D, 4*A, A*B, B*2, A*2, C/C.

import numpy as np

# Definicje macierzy
A = np.array([[1, 2], [4, 5], [7, 8]])        # shape: (3, 2)
B = np.array([[2, 0, 0], [0, 2, 0], [0, 0, 2]])  # shape: (3, 3)
C = np.array([2, 1, 0])                        # shape: (3,)
D = np.array([[3, 2, 1], [0, 5, 6], [8, -1, 2]]) # shape: (3, 3)

print("Shapes:")
print("A:", A.shape)
print("B:", B.shape)
print("C:", C.shape)
print("D:", D.shape)
print()

def try_addition(X, Y, name):
    if X.shape == Y.shape:
        print(f"{name}:\n{X + Y}\n")
    else:
        print(f"{name} - nie można wykonać: różne kształty {X.shape} vs {Y.shape}\n")

def try_multiplication(X, Y, name):
    if X.shape[1] == Y.shape[0]:
        print(f"{name}:\n{X @ Y}\n")
    else:
        print(f"{name} - nie można wykonać: {X.shape[1]} != {Y.shape[0]}\n")

# 1. B + D
try_addition(B, D, "B + D")

# 2. 3 * A
print("3 * A:\n", 3 * A, "\n")

# 3. -2 * C
print("-2 * C:\n", -2 * C, "\n")

# 4. B * A (mnożenie macierzowe)
try_multiplication(B, A, "B * A")

# 5. D * B
try_multiplication(D, B, "D * B")

# 6. 2 * A + B - C → niemożliwe, ale możemy sprawdzić
try:
    result = 2 * A + B - C
    print("2 * A + B - C:\n", result, "\n")
except Exception as e:
    print("2 * A + B - C - nie można wykonać:", e, "\n")

# 7. C * D - D * C (mnożenie macierzowe)
try:
    CD = C @ D
    DC = D @ C
    print("C @ D - D @ C:\n", CD - DC, "\n")
except Exception as e:
    print("C @ D - D @ C - nie można wykonać:", e, "\n")

# 8. 2 * B - D
try_addition(2 * B, -D, "2 * B - D")

# 9. D * D
try_multiplication(D, D, "D * D")

# 10. B * B + D * D
try:
    result = B @ B + D @ D
    print("B @ B + D @ D:\n", result, "\n")
except Exception as e:
    print("B @ B + D @ D - nie można wykonać:", e, "\n")

# 11. 4 * A
print("4 * A:\n", 4 * A, "\n")

# 12. A * B
try_multiplication(A, B, "A * B")

# 13. B * 2
print("B * 2:\n", B * 2, "\n")

# 14. A * 2
print("A * 2:\n", A * 2, "\n")

# 15. C / C
try:
    result = C / C
    print("C / C:\n", result, "\n")
except Exception as e:
    print("C / C - nie można wykonać:", e, "\n")
