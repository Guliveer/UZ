# Prompting the user for a file name and displaying its content
file_name = input("Enter the file name: ")

with open(file_name, 'r', encoding='utf-8') as file:
    content = file.read()
    print('\n' + content)