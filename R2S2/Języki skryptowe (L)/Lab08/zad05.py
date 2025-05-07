# Narysować wykres y = x^2 w zakresie (−3, 2). Wyznaczyć styczne do funkcji w punktach
# {−2, 0, 1}. Narysować wykres i styczne w różnych kolorach.

import numpy as np
import matplotlib.pyplot as plt

# Funkcja i jej pochodna
def f(x):
    return x**2

def df(x):
    return 2*x

# Zakres dla wykresu funkcji
x_vals = np.linspace(-3, 2, 400)
y_vals = f(x_vals)

# Punkty, w których liczymy styczne
points = [-2, 0, 1]
colors = ['red', 'green', 'blue']

plt.figure(figsize=(8, 6))

# Rysowanie funkcji
plt.plot(x_vals, y_vals, label='y = x²', color='black')

# Rysowanie stycznych
for a, color in zip(points, colors):
    slope = df(a)         # pochodna w punkcie
    y0 = f(a)             # wartość funkcji w punkcie
    tangent = y0 + slope * (x_vals - a)  # równanie stycznej
    plt.plot(x_vals, tangent, label=f'Styczna w x = {a}', color=color)

# Ozdoby
plt.title('Wykres y = x² i styczne w punktach x = -2, 0, 1')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.grid(True)
plt.axhline(0, color='gray', linewidth=0.5)
plt.axvline(0, color='gray', linewidth=0.5)
plt.show()
