# Zdefiniować funkcje jednoargumentowe ceil oraz floor realizujące odpowiednio zadania
# zaokrąglania liczb rzeczywistych w górę oraz w dół (do liczb całkowitych) bez importowania
# dodatkowych modułów. (Wykorzystać operator dzielenia modulo % lub dzielenia całkowitoliczbowego
# z zaokrąglaniem w dół //). Sprawdzić działanie funkcji dla dowolnie wybranych 5 liczb
# rzeczywistych oraz 2 liczb całkowitych. Zwrócić wynik działania funkcji na ekran.

# Zaokrąglanie w górę
def ceil(x):
    # Jeśli x jest liczbą całkowitą, zwróć ją
    if x == int(x):
        return int(x)
    # W przeciwnym razie, jeśli x jest liczbą ujemną, zwróć x - 1
    return int(x) + (1 if x > 0 else 0)

# Zaokrąglanie w dół
def floor(x):
    # Jeśli x jest liczbą całkowitą, zwróć ją
    if x == int(x):
        return int(x)
    # W przeciwnym razie, jeśli x jest liczbą dodatnią, zwróć x
    return int(x) - (1 if x < 0 and x % 1 != 0 else 0)

test_values = [3.7, -2.3, 5.0, -0.8, 2.999, 4, -7]

for value in test_values:
    f = int(floor(value))
    c = int(ceil(value))
    print(f"x = {value:.3f}\tfloor(x) = {f}\tceil(x) = {c}")