// Napisać funkcję, która nic nie zwraca, przyjmującą jako argument jedną liczbę całkowitą. W funkcji
// zwiększyć wartość argumentu o dwa. Zmiana ma być widoczna na zewnątrz funkcji. Wykorzystać (dla
// każdego mechanizmu napisać oddzielną funkcję):
// - przekazanie argumentu przez wskaźnik
// - przekazanie argumentu przez referencję
//
// Napisać program demonstrujący podobieństwa i różnice między wywołaniem obu funkcji.

#include <iostream>
using namespace std;

void zwieksz_wskaznik(int *a) {
    *a += 2;
}

void zwieksz_referencja(int &a) {
    a += 2;
}

int main() {
    cout << "Podaj liczbe calkowita: ";
    int liczba;
    cin >> liczba;
    cout << "\n";

    int x = liczba; // Zapisanie liczby do zmiennej x, aby moc przywrocic ja do wartosci poczatkowej

    cout << "Zwiekszanie przez wskaznik..." << endl;
    zwieksz_wskaznik(&x);
    cout << "Liczba po zwiekszeniu: " << x << endl;

    x = liczba; // Przywrócenie x do wartosci poczatkowej
    cout << "Zwiekszanie przez referencje..." << endl;
    zwieksz_referencja(x);
    cout << "Liczba po zwiekszeniu: " << x << endl;
    return EXIT_SUCCESS;
}
