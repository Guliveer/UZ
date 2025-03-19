# Jak obsługuje się wyjątki w Pythonie?

try:
    # Kod, który może wywołać wyjątek
    result = 10 / 0
except ZeroDivisionError:
    # Kod obsługujący wyjątek
    print("Nie można dzielić przez zero!")
else:
    # Kod, który wykona się, jeśli nie wystąpi wyjątek
    print("Wynik:", result)
finally:
    # Kod, który wykona się niezależnie od tego, czy wystąpił wyjątek, czy nie
    print("Koniec obsługi wyjątków.")