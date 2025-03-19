# Podać cztery operacje modyfikujące obiekt listy.

# Original list
original_list = [1, 2, 3, 4, 5]

# 1. Append: Adding an element to the end of the list
original_list.append(6)

# 2. Remove: Removing the first occurrence of an element
original_list.remove(3)

# 3. Insert: Inserting an element at a specific position
original_list.insert(2, 10)

# 4. Pop: Removing and returning an element at a specific position
popped_element = original_list.pop(4)

# Displaying the results
print("Original List:", original_list)
print("Modified List:", original_list)
print("Popped Element:", popped_element)
