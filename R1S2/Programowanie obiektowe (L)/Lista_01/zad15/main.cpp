// Napisać program, który dla dwóch dat podanych jako dzień1, miesiąc1, rok1, dzień2, miesiąc2, rok2 i określi, która z nich jest wcześniejsza. Założyć, że wartości dat wprowadzane będą poprawnie — pominąć kontrolę błędów.
#include <iostream>

using namespace std;

int main() {
    int dzien1, miesiac1, rok1, dzien2, miesiac2, rok2;
    cout << "Podaj pierwsza date (dzien, miesiac, rok): ";
    cin >> dzien1 >> miesiac1 >> rok1;

    cout << "Podaj druga date (dzien, miesiac, rok): ";
    cin >> dzien2 >> miesiac2 >> rok2;

    if (rok1 < rok2) {
        cout << "Pierwsza data jest wczesniejsza" << endl;
        return EXIT_SUCCESS;
    }
    if (rok1 > rok2) {
        cout << "Druga data jest wczesniejsza" << endl;
        return EXIT_SUCCESS;
    }

    if (miesiac1 < miesiac2) {
        cout << "Pierwsza data jest wczesniejsza" << endl;
        return EXIT_SUCCESS;
    }
    if (miesiac1 > miesiac2) {
        cout << "Druga data jest wczesniejsza" << endl;
        return EXIT_SUCCESS;
    }

    if (dzien1 < dzien2) {
        cout << "Pierwsza data jest wczesniejsza" << endl;
        return EXIT_SUCCESS;
    }
    if (dzien1 > dzien2) {
        cout << "Druga data jest wczesniejsza" << endl;
        return EXIT_SUCCESS;
    }

    cout << "Daty sa takie same" << endl;
    return EXIT_SUCCESS;
}
