// Napisać deklaracje następujących zmiennych:
// - wskaźnik do znaku,
// - tablica pięciu wskaźników do liczb rzeczywistych,
// - wskaźnik do stałej całkowitej,
// - stały wskaźnik do stałej znakowej,
// - wskaźnik do pięcioelementowej tablicy liczb rzeczywistych

#include <iostream>
using namespace std;

int main() {
    cout << "Wskaźnik do znaku: *char" << endl;
    cout << "Tablica pięciu wskaźników do liczb rzeczywistych: *double[5]" << endl;
    cout << "Wskaźnik do stałej całkowitej: const int*" << endl;
    cout << "Stały wskaźnik do stałej znakowej: const char* const" << endl;
    cout << "Wskaźnik do pięcioelementowej tablicy liczb rzeczywistych: double(*)[5]" << endl;

    return EXIT_SUCCESS;
}
