// Dany jest tekst złożony ze znaków ASCII podzielony na zdania. Koniec każdego zdania jest oznaczony
// przez ’.’. Tekst należy wczytać do tablicy jednowymiarowej i policzyć słowa w każdym zdaniu osobno.

#include <iostream>
using namespace std;

int main() {
    int words=1;
    constexpr char str[]= "Ala ma kota. Kot jej nie kocha. Ala wyjebała kota z domu.";

    // Split string into sentences. If ' ' and '.' are neighbors, remove ' '.
    for (int i=0; i<sizeof(str); i++) {
        if (str[i] == '.') {
            cout << "Words in sentence: " << words << endl;
            words = 0;
        }
        if (str[i] == ' ' || str[i] == '.' || str[i] == '\'') {
            words++;
        }
        if (str[i] == '.' && str[i+1] == ' ') {
            words--;
        }
    }


    return EXIT_SUCCESS;
}
