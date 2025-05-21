# Jakie liczby dziesiętne reprezentują podane poniżej liczby binarne, przy założeniach,
# że liczba cyfr mantysy to 4, a liczba cyfr cechy bez znaku to 2:
# (a) 10101101
# (b) 11101010
# (c) 01001000
# (d) 01111011
# (e) 01000111
# Napisz program, który przekonwertuje te liczby na system dziesiętny.
# Nieoczekiwane wyniki obliczeń, uzasadnij otrzymane wyniki dla podanych przypadków:


def bin_to_float(bits: str) -> float:
    if len(bits) != 8:
        raise ValueError("Input must be 8 bits")
    sign = -1 if bits[0] == "1" else 1
    exponent = int(bits[2:4], 2)
    mantissa_val = int(bits[4:], 2) / 16  # 4 bits: .xxxx
    value = sign * (2 ** exponent) * mantissa_val
    return value

examples = [
    "10101101",
    "11101010",
    "01001000",
    "01111011",
    "01000111",
]

for val in examples:
    result = bin_to_float(val)
    explanation = ""
    mantissa = int(val[3:], 2)
    if mantissa == 0:
        explanation = " (mantissa is zero, so value is zero)"
    elif int(val[1:3], 2) == 0 and mantissa != 0:
        explanation = " (exponent is zero, so value is just mantissa with sign)"
    print(f"{val} = {result}{explanation}")