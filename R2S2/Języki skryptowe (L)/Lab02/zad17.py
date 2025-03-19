# Podać dwa typy niezmiennicze.

def explain_immutable_types():
    explanation = """
    Dwa typy niezmiennicze w Pythonie to:

    1. tuple (krotka) - jest to uporządkowana kolekcja elementów, która jest niezmienna, co oznacza, że
        po utworzeniu krotki nie można zmienić jej elementów.
    2. str (łańcuch znaków) - jest to sekwencja znaków, która jest niezmienna, co oznacza, że
        po utworzeniu łańcucha znaków nie można zmienić jego zawartości.

    Przykłady użycia:
    krotka = (1, 2, 3)
    lancuch = "Hello, World!"

    print(f"Krotka: {krotka}, typ: {type(krotka)}")
    print(f"Łańcuch: {lancuch}, typ: {type(lancuch)}")
    """
    print(explanation)

explain_immutable_types()
