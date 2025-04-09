# Reading the content of "Inwokacja" from the file
with open('inwokacja.txt', 'r', encoding='utf-8') as file:
    inw = file.read()

# Displaying "Inwokacja" in various formats
# 1. All lowercase
print("\n[ Małymi literami ]")
print(inw.lower())

# 2. All uppercase
print("\n[ Wielkimi literami ]")
print(inw.upper())

# 3. Each line starting with a capital letter
print("\n[ Każdy wers z wielkiej litery ]")
print("\n".join(line.capitalize() for line in inw.splitlines()))

# 4. Alternating lines: one uppercase, one lowercase
print("\n[ Co drugi wers z wielkiej litery, co drugi z małej ]")
lines = inw.splitlines()
print("\n".join(line.upper() if i % 2 == 0 else line.lower() for i, line in enumerate(lines)))

# 5. Alternating characters: one uppercase, one lowercase
print("\n[ Co druga litera wielka, co druga mała ]")
alternating_case = ''.join(
    char.upper() if i % 2 == 0 else char.lower() for i, char in enumerate(inw)
)
print(alternating_case)

# 6. Every second character
print("\n[ Co druga litera ]")
print(inw[::2])

# 7. Reversed order
print("\n[ W porządku odwróconym ]")
print(inw[::-1])