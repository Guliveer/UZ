# Zdefiniować funkcje realizujące dwuargumentowe dodawanie, odejmowanie, mnożenie i dzielenie,
# a następnie wywołać je w kolejności realizującej następujące równanie
#
# x = ((4 + 2 * (5 − 10)) / (32 * 11 − 4)) + 2
#
# Zwrócić wynik do konsoli


def add(a, b):
    return a + b

def subtract(a, b):
    return a - b

def multiply(a, b):
    return a * b

def divide(a, b):
    if b == 0:
        raise ValueError("Division by zero is not allowed")
    return a / b


step1 = subtract(5, 10)  # (5 − 10)
step2 = multiply(2, step1)  # 2 * (5 − 10)
step3 = add(4, step2)  # 4 + 2 * (5 − 10)
step4 = multiply(32, 11)  # 32 * 11
step5 = subtract(step4, 4)  # (32 * 11 − 4)
step6 = divide(step3, step5)  # ((4 + 2 * (5 − 10)) / (32 * 11 − 4))
result = add(step6, 2)  # ((4 + 2 * (5 − 10)) / (32 * 11 − 4)) + 2

print(result)