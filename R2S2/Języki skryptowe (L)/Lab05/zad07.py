import os

with open('inwokacja.txt', 'r', encoding='utf-8') as file:
    inw = file.read()

# Ensure the output directory exists
os.makedirs('zad07_output', exist_ok=True)

# Splitting the text into words
words = inw.split()

# Saving each word to a separate file
for i, word in enumerate(words, start=1):
    with open(f'zad07_output/word_{i}.txt', 'w', encoding='utf-8') as word_file:
        word_file.write(word)