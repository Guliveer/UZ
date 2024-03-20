// Program obliczający sumę cyfr liczb parzystych liczb od 40 do 60 włącznie
#include <iostream>
using namespace std;

int main() {
    int dziesiatki = 4, jednosci = 0, suma = 0; // Deklaracja zmiennych początkowy (początek zakresu liczb)
    int max_dz = 6, max_j = 0; // Deklaracja gornego zakresu liczb

    while (dziesiatki < max_dz + 1) {
        if (jednosci % 2 == 0 && jednosci < 10) { // Jesli jednosci jest parzyste i mniejsze od 10
            suma += jednosci + dziesiatki; // Dodajemy do sumy
        }

        cout << suma << " += " << dziesiatki << " + " << jednosci << endl; // Wyswietlamy wynik

        if (jednosci < 8) { // Jezeli jednosci sa mniejsze od 8, bo wyswietlamy tylko cyfry od 0 do 8 (parzyste)
            jednosci += 2; // Zwiekszamy jednosci o 2
        } else { // Jezeli nie
            jednosci = 0; // Zerujemy jednosci
            dziesiatki += 1; // Zwiekszamy dziesiatki o 1
        }

        if (dziesiatki == max_dz && jednosci > max_j) { // Jezeli dziesiatki sa rowne max_dz i jednosci jest rowne max_j
            return 1; // Wyjdź z pętli
        }
    }

    return 0;
}
