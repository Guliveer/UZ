#include <iostream>

class Kontener {
    int roz;
    double *wsk;
public:
    // Zdefiniuj konstruktor Kontener(int r=3)... z listą inicjalizacyjną
    explicit Kontener(int r = 3) : roz(r) {
        wsk = new double[roz];
    }

    // Zdefiniuj operator(), który przyjmuje 2 argumenty int "idx" i double "wartosc"
    // i ma za zadanie wstawić do kontenera "wartosc" w miejsce o nnumerze "idx".
    // Funkcja zwraca 1 jeśli operacja się powiodła lub 0, gdy kontener nie posiada miejsca o danym indeksie "idx".
    int operator()(int idx, double wartosc) { // ewentualnie typu "bool" zamiast "int"
        if (idx < 0 || idx >= roz) {
            return 0;
        }
        wsk[idx] = wartosc;
        return 1;
    }
};

int main() {
    Kontener k;
    std::cout << k(3, 3.14) << std::endl; // result: 0
    std::cout << k(5, 3.14) << std::endl; // result: 0

    Kontener j(5);
    std::cout << j(3, 3.14) << std::endl; // result: 1
    std::cout << j(5, 3.14) << std::endl; // result: 0

    return EXIT_SUCCESS;
}
