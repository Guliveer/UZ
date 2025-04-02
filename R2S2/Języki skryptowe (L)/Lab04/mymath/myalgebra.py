# additionally optimized

def elementwise_operation(a, b, op):
    if isinstance(a[0], list) and isinstance(b[0], list):
        if len(a) != len(b) or len(a[0]) != len(b[0]):
            raise ValueError("Matrices must have the same dimensions for addition or subtraction.")
        return [[op(x, y) for x, y in zip(row_a, row_b)] for row_a, row_b in zip(a, b)]
    return [op(x, y) for x, y in zip(a, b)]

def dodaj(a, b):
    return elementwise_operation(a, b, lambda x, y: x + y)

def odejmij(a, b):
    return elementwise_operation(a, b, lambda x, y: x - y)

def pomnoz(a, b):
    if isinstance(a[0], list) and isinstance(b[0], list):
        if len(a[0]) != len(b):
            raise ValueError("Number of columns in the first matrix must equal the number of rows in the second matrix for multiplication.")
        return [[sum(x * y for x, y in zip(row_a, col_b)) for col_b in zip(*b)] for row_a in a]
    return a * b

def print_matrix(matrix):
    for row in matrix:
        print(" ".join(map(str, row)))