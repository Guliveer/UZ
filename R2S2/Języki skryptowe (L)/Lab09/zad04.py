# Przedstawić liczbę –245,25 w zapisie stałopozycyjnym i zmiennopozycyjnym przy bazie 2.
# Ile bitów minimalnie potrzeba?

number = -245.25

# Fixed-point representation
sign_bit = 1
int_part = abs(int(number))
frac_part = abs(number) - int_part

int_bin = bin(int_part)[2:]
frac_bin = ""
frac = frac_part
for _ in range(2):  # 2 bits for .25
    frac *= 2
    bit = int(frac)
    frac_bin += str(bit)
    frac -= bit

fixed_point = f"-{int_bin}.{frac_bin}"
fixed_point_bits = sign_bit + len(int_bin) + len(frac_bin)

print(f"Fixed-point: {number} = {fixed_point} (min. {fixed_point_bits} bits)")

# Floating-point (normalized) representation

# Find exponent for normalization
if int_part != 0:
    exponent = len(int_bin) - 1
    mantissa_val = abs(number) / (2 ** exponent)
else:
    # For numbers < 1, find first '1' in fraction
    mantissa_val = abs(number)
    exponent = 0
    while mantissa_val < 1:
        mantissa_val *= 2
        exponent -= 1

# Get mantissa in binary (10 bits after the point)
mantissa_bin = ""
mantissa = mantissa_val - 1
for _ in range(10):
    mantissa *= 2
    bit = int(mantissa)
    mantissa_bin += str(bit)
    mantissa -= bit

print(f"Floating-point: -1.{mantissa_bin} * 2^{exponent}")

# Minimal bits: 1 sign, 10 mantissa, 4 exponent (enough for exponent 7)
print(f"Minimal bits needed: {1 + 10 + 4}")