# Narysować wykres sin(x). Przesunąć w funkcje w fazie (sin(x+φ)) o {1/4, 1/3, 1/2} radianów.
# Nanieść wszystkie funkcje na jeden wykres, w różnych kolorach. Narysować i przesunąć
# w fazie funkcję cos(x), wykresy narysować linią przerywaną.

import numpy as np
import matplotlib.pyplot as plt

# Zakres wartości x
x = np.linspace(-2 * np.pi, 2 * np.pi, 1000)

# Fazy przesunięcia
phases = [1/4, 1/3, 1/2]
colors = ['red', 'green', 'blue']

# Rysowanie sin(x) i przesunięć
plt.figure(figsize=(10, 6))
plt.plot(x, np.sin(x), label='sin(x)', color='black')

for phi, color in zip(phases, colors):
    plt.plot(x, np.sin(x + phi), label=f'sin(x + {phi})', color=color)

# Rysowanie cos(x) i przesunięć – linia przerywana
plt.plot(x, np.cos(x), label='cos(x)', color='black', linestyle='--')

for phi, color in zip(phases, colors):
    plt.plot(x, np.cos(x + phi), label=f'cos(x + {phi})', color=color, linestyle='--')

# Opis wykresu
plt.title('sin(x) i cos(x) z przesunięciem fazowym')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.grid(True)
plt.show()
