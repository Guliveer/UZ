with open('inwokacja.txt', 'r', encoding='utf-8') as file:
    inw = file.read()

# Removing all spaces
inw_no_spaces = inw.replace(" ", "")

# Displaying the result
print(inw_no_spaces)