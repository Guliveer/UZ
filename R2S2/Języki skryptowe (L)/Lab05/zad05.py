# Reading the content of "Inwokacja" from the file
with open('inwokacja.txt', 'r', encoding='utf-8') as file:
    inw = file.read()

# Creating different versions of "Inwokacja"
lowercase = inw.lower()
uppercase = inw.upper()
capitalized_lines = "\n".join(line.capitalize() for line in inw.splitlines())
alternating_lines = "\n".join(
    line.upper() if i % 2 == 0 else line.lower() for i, line in enumerate(inw.splitlines())
)
alternating_characters = ''.join(
    char.upper() if i % 2 == 0 else char.lower() for i, char in enumerate(inw)
)
every_second_character = inw[::2]
reversed_order = inw[::-1]

# Storing all versions in a dictionary for comparison
versions = {
    "lowercase": lowercase,
    "uppercase": uppercase,
    "capitalized_lines": capitalized_lines,
    "alternating_lines": alternating_lines,
    "alternating_characters": alternating_characters,
    "every_second_character": every_second_character,
    "reversed_order": reversed_order,
}

# Comparing versions using equality, less than, and greater than operators
for name1, version1 in versions.items():
    for name2, version2 in versions.items():
        if name1 != name2:
            print(f"Comparing {name1} and {name2}:")
            print(f"\t{name1}\t==\t{name2}:\t{version1 == version2}")
            print(f"\t{name1}\t<\t{name2}:\t{version1 < version2}")
            print(f"\t{name1}\t>\t{name2}:\t{version1 > version2}")
            print()