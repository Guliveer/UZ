def dodaj(a, b):
    if isinstance(a, list) and isinstance(b, list):
        return [x + y for x, y in zip(a, b)]
    return a + b

def odejmij(a, b):
    if isinstance(a, list) and isinstance(b, list):
        return [x - y for x, y in zip(a, b)]
    return a - b

def pomnoz(a, b):
    if isinstance(a, list) and isinstance(b, list):
        return [x * y for x, y in zip(a, b)]
    return a * b