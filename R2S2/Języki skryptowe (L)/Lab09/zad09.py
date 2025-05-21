# Sprawdzić wynik działania: sqrt(1e-16 + 1) - 1

import math

result = math.sqrt(1e-16 + 1) - 1
print(f"sqrt(1e-16 + 1) - 1 = {result}")

# Przy bardzo małych liczbach może dochodzić do utraty precyzji (cancellation error).
