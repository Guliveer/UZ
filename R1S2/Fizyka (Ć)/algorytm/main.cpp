#include <cmath>
#include <iostream>

using namespace std;

int main() {
    double start;
    double end;
    double growthRate = 3e-9; // tempo zwiekszania przyrostu | dla dokladniejszych wynikow zmniejsz wartość
    int prec = 17; // dokładność obliczeń


        start = 3800; // wartość początkowa
        end = 3810; // wartość końcowa

//    cout << "Podaj wartosc poczatkowa: "; cin >> start;
//    cout << "Podaj wartosc koncowa: "; cin >> end;

    if (start > end) {
//        cout << "Wprowadzono niepoprawne dane. Wartosc poczatkowa nie moze byc wieksza od koncowej.";
//        return EXIT_FAILURE;
    // todo: ujemne
    }


    double segment = start; // zaczynamy od początku drogi
    double k = 0.00023035;
    int count = 1; // zaczynamy od 1, bo mamy co najmniej jeden odcinek w drodze
    double growth = 0.0; // przyrost poczatkowy

    ////// Główna funkcjonalność //////
    while (segment + growth < end) {
        while (log((growth + segment) / segment) <= k) {
            growth += growthRate;
        }

        // Usuń komentarz z poniższego wiersza, aby zobaczyć współrzędne każdego odcinka
        //cout << setprecision(prec) << "Odcinek: " << count << " | Wspolrzedne: " << segment << endl;

        if (segment + growth < end) {
            segment += growth;
            growth*=0.95;
            count++;
        }
    }

    cout << "\n-----\n";
    cout << "Ilosc odcinków: " << count << endl;
//    cout << setprecision(prec) << "Wspolrzedne ostatniego odcinka: " << segment << endl;
//    cout << "Dlugosc ostatniego odcinka: " << abs(end - segment) << endl;
    return EXIT_SUCCESS;
}
