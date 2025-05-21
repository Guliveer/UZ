# Przy mierzeniu pewnej wielkości uzyskano wynik p = 32.65.
# Wiedząc, że maksymalny błąd względny tego pomiaru wynosi 0.5%
# znaleźć przedział, w którym zawarta jest wielkość p.

# p = 32.65, maksymalny błąd względny = 0.5% → oblicz przedział wartości

p = 32.65
relative_error = 0.5 / 100  # 0.5%

abs_error = p * relative_error
lower_bound = p - abs_error
upper_bound = p + abs_error

print(f"Przedział pomiaru: ({lower_bound}, {upper_bound})")
