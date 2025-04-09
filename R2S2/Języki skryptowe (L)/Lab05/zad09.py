with open('inwokacja.txt', 'r', encoding='utf-8') as file:
    inw = file.read()

# Replacing "w" with "{w[n]}" where n is a hexadecimal number
counter = 1
formatted_inw = ""
for char in inw:
    if char == "w":
        formatted_inw += f"{{w{counter:04x}}}"
        counter += 1
    else:
        formatted_inw += char

# Displaying the result
print(formatted_inw)