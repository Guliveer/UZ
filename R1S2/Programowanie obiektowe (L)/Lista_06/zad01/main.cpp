// Zadeklarowac i zdefiniowac:

// - konstruktor inicjalizujący (zastosować listę inicjalizacyjną). Zmienną czyKopia zawsze ustawić na wartość false,
// - metodę dodaj umozliwiajaca dodanie dwóch liczb zespolonych. Wynikiem dodawania jest liczba zespolona,
// - dwie wersje funkcji operatorowej + umożliwiającej dodanie dwóch liczb zespolonych
//// - w wersji globalnej
//// - jako funkcja składowa klasy LiczbaZespolona
//// Zmienną czyKopia dla wyniku zawsze ustawić na wartość false,

#include <iostream>

using namespace std;

class LiczbaZespolona {
    int Re;
    int Im;
    char nazwa;
    bool czyKopia;

public:
    LiczbaZespolona(int Re, int Im, char nazwa) : Re(Re), Im(Im), nazwa(nazwa), czyKopia(false) {};

    LiczbaZespolona dodaj(LiczbaZespolona const &l) {
        // dodawanie poprzez nadpisanie wartości obiektu
        this->Re += l.Re;
        this->Im += l.Im;
        return *this;
    }

    LiczbaZespolona operator+(LiczbaZespolona const &l) const {
        // dodawanie poprzez zwrócenie nowego obiektu
        return {this->Re + l.Re, this->Im + l.Im, 'c'};
    }
};

int main() {


    return EXIT_SUCCESS;
}
