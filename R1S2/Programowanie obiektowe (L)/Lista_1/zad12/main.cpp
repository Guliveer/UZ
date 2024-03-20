// Dla tablicy znakowej odwróć kolejność elementów
#include <iostream>

int main() {
    constexpr char tab[] = "Hello, World!";
    for (int i = sizeof(tab) - 2; i >= 0; --i) {
        std::cout << tab[i];
    }
    return 0;
}
