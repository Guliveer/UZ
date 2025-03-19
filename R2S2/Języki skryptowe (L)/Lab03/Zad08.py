# Utworzyć słownik w którym kluczami są adresy email, a wartościami
# imiona i nazwiska, a następnie zwrócić posortowany słownik.

data = {
    "will.smith@example.com": "Will Smith",
    "yo.mama@example.com": "Yo Mama",
    "ben.dover@example.com": "Ben Dover",
    "ice.tink@example.com": "Ice Tink",
}

# Sort the dictionary by keys (email addresses)
sorted_data = dict(sorted(data.items()))

print("Posortowany słownik:", sorted_data)