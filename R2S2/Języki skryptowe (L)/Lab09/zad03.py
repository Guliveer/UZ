# Przy założeniu, że zapisujemy liczbę w pamięci operacyjnej binarnie w zapisie stałopozycyjnym ze znakiem,
# podać zakres liczb, które możemy przedstawić na 10 bitach.
bits = 10
left_min = -2 ** (bits - 1)
left_max = 2 ** (bits - 1) - 1
print(f"Fixed-point ({bits} bits): {left_min} - {left_max}")

# Zmiennopozycyjny: mantysa 5 bitów, 1 bit znak, 4 bity wykładnik (w tym znak cechy)
mantissa_bits = 5
exponent_bits = 4
bias = 2**(exponent_bits - 1) - 1
mantissa_max = 1 - 2 ** -mantissa_bits
exponent_range = (-bias, bias)
print(f"Floating-point (mantissa {mantissa_bits} bits): exponent range: {exponent_range}")
print(f"Example of the maximum approximate value: {mantissa_max} * 2^{bias}")