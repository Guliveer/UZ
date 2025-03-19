# Podać cztery operacje modyfikujące obiekt słownika.

# Original dictionary
original_dict = {'a': 1, 'b': 2, 'c': 3}

# 1. Add a new key-value pair
original_dict['d'] = 4

# 2. Update the value of an existing key
original_dict['a'] = 10

# 3. Remove a key-value pair using pop
removed_value = original_dict.pop('b')

# 4. Clear all key-value pairs
original_dict.clear()

# Displaying the results
print("Modified Dictionary:", original_dict)
print("Removed Value:", removed_value)
