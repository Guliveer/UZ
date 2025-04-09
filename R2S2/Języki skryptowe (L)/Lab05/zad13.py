# Prompting the user for a file name and displaying its content with exception handling
file_name = input("Enter the file name: ")

try:
    with open(file_name, 'r', encoding='utf-8') as file:
        content = file.read()
        print("File content:\n")
        print(content)
except FileNotFoundError:
    print(f"Error: The file '{file_name}' does not exist.")
except Exception as e:
    print(f"An unexpected error occurred: {e}")