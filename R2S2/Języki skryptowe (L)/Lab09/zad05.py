# Napisz funkcję dectobin nie używając wbudowanych funkcji w Pythonie

def dectobin(n: int) -> str:
    if n == 0:
        return "0"
    sign = "-" if n < 0 else ""
    n = abs(n)
    result = ""
    while n > 0:
        result = str(n % 2) + result
        n //= 2
    return sign + result

# Przykład
print(dectobin(10))    # 1010
print(dectobin(-18))   # -10010