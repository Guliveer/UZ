# Zapisać dziesiętnie:
# a) (100111)₂
# b) (111001001101)₂
# c) (77)₈
# d) (263)₈
# e) (7F)₁₆
# f) (F8FE)₁₆
# g) (1A6.E2)₁₆
# h) (77.44)₈
# i) (111101.101)₂

# a)
print(f"a) (100111)₂ = {int('100111', 2)}")

# b)
print(f"b) (111001001101)₂ = {int('111001001101', 2)}")

# c)
print(f"c) (77)₈ = {int('77', 8)}")

# d)
print(f"d) (263)₈ = {int('263', 8)}")

# e)
print(f"e) (7F)₁₆ = {int('7F', 16)}")

# f)
print(f"f) (F8FE)₁₆ = {int('F8FE', 16)}")

# g)
hexa = "1A6.E2"
whole = int(hexa.split('.')[0], 16)
fraction = sum(int(d, 16) * 16**-i for i, d in enumerate(hexa.split('.')[1], 1))
print(f"g) (1A6.E2)₁₆ = {whole + fraction}")

# h)
octal = "77.44"
whole, frac = octal.split('.')
whole = int(whole, 8)
fraction = sum(int(d, 8) * 8**-i for i, d in enumerate(frac, 1))
print(f"h) (77.44)₈ = {whole + fraction}")

# i)
binary = "111101.101"
whole, frac = binary.split('.')
whole = int(whole, 2)
fraction = sum(int(d) * 2**-i for i, d in enumerate(frac, 1))
print(f"i) ({binary})₂ = {whole + fraction}")