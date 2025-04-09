import string

with open('inwokacja.txt', 'r', encoding='utf-8') as file:
    inw = file.read()

# Replacing "a" with "{}" and cycling through the alphabet
alphabet = string.ascii_lowercase
formatted_inw = inw
alphabet_length = len(alphabet)

counter = 0
while "a" in formatted_inw:
    formatted_inw = formatted_inw.replace("a", f"{{{alphabet[counter % alphabet_length]}}}", 1)
    counter += 1

# Displaying the result
print(formatted_inw)