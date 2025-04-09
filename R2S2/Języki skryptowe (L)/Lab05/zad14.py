# Read the content of plik.txt
with open('plik.txt', 'r', encoding='utf-8') as file:
    lines = file.readlines()

# Process the lines
processed_lines = []
for index, line in enumerate(lines):
    if index % 2 == 0:  # Even-indexed lines
        processed_lines.append(line.strip() + '!')
    else:  # Odd-indexed lines
        processed_lines.append(line.strip() + '?')

# Get the output file name from the user
output_file = input("Enter the name of the output file: ")

# Write the processed lines to the output file
with open(output_file, 'w', encoding='utf-8') as file:
    file.write('\n'.join(processed_lines))