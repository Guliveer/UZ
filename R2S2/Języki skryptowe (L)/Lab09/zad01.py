# Zapisać w systemie dwójkowym, ósemkowym (oktalnym) i szesnastkowym (heksadecymalnym) liczby systemu dziesiętnego:
# a) 24
# b) 232
# c) 1025
# d) 46 - 1
# e) 125,625
# f) 0,325

def float_to_bin(val, digits=10) -> bin:
    whole, frac = int(val), val - int(val)
    result = "0b" + bin(whole)[2:]
    if frac != 0:
        result += "."
        for _ in range(digits):
            frac *= 2
            bit = int(frac)
            result += str(bit)
            frac -= bit
        result = result.rstrip("0")  # remove trailing zeros
        if result[-1] == ".":  # remove dot if nothing after
            result = result[:-1]
    return result

def float_to_oct(val, digits=10) -> oct:
    whole, frac = int(val), val - int(val)
    result = "0o" + oct(whole)[2:]
    if frac != 0:
        result += "."
        for _ in range(digits):
            frac *= 8
            digit = int(frac)
            result += str(digit)
            frac -= digit
        result = result.rstrip("0")
        if result[-1] == ".":
            result = result[:-1]
    return result

def float_to_hex(val, digits=10) -> hex:
    whole, frac = int(val), val - int(val)
    hex_digits = "0123456789abcdef"
    result = "0x" + hex(whole)[2:]
    if frac != 0:
        result += "."
        for _ in range(digits):
            frac *= 16
            digit = int(frac)
            result += hex_digits[digit]
            frac -= digit
        result = result.rstrip("0")
        if result[-1] == ".":
            result = result[:-1]
    return result

def print_conversion(val, binary, octal, hexadecimal) -> None:
    print(f"[{val}]"
        f"\n\tbin: {binary}"
        f"\n\toct: {octal}"
        f"\n\thex: {hexadecimal}")

def convert_array(vals):
    for val in vals:
        if isinstance(val, int) or (isinstance(val, float) and val.is_integer()):
            # Treat as integer
            print_conversion(val, bin(int(val)), oct(int(val)), hex(int(val)))
        else:
            # Treat as float
            print_conversion(val, float_to_bin(val), float_to_oct(val), float_to_hex(val))

values = [
    24, # a)
    232, # b)
    1025, # c)
    46 - 1, # d)
    125.625, # e)
    0.325, # f)
]

convert_array(values)
