# Prompting the user for a file name and displaying a specific line with exception handling
file_name = input("Enter the file name: ")

try:
    with open(file_name, 'r', encoding='utf-8') as file:
        lines = file.readlines()

    # Prompt the user for the line number
    line_number = int(input("Enter the line number to display: "))

    # Check if the line number is valid
    if 1 <= line_number <= len(lines):
        print(f"Line {line_number}: {lines[line_number - 1].strip()}")
    else:
        print(f"Error: Line {line_number} does not exist in the file.")
except FileNotFoundError:
    print(f"Error: The file '{file_name}' does not exist.")
except ValueError:
    print("Error: Please enter a valid number for the line.")
except Exception as e:
    print(f"An unexpected error occurred: {e}")