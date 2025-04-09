with open('inwokacja.txt', 'r', encoding='utf-8') as file:
    inw = file.read()

lines = inw.splitlines()  # Podział na linie
joined_inw = "\n".join(lines)  # Scalanie linii w jeden łańcuch z separatorem

print(joined_inw)  # Wyświetlenie scalonego tekstu