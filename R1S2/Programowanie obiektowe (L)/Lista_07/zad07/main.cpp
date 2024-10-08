/*
 * Wprowadzić mechanizmy polimorficzne do klasy ListaOsob:
 * - Wprowadzić odpowiednie dziedziczenia pomiędzy klasami Student i Czlowiek.
 * - Zaimplementować funkcje w klasach Student i Czlowiek jako wirtualne.
 * - Zaimplementować wirtualne destruktory w klasach Student i Czlowiek (w przykładzie nie są potrzebne, ale to zawsze dobra praktyka!).
 * - Zmienić implementację klasy ListaOsob tak, by korzystała z polimorfizmu i funkcji wirtualnych.
 * - Porównać stopień skomplikowania wszystkich klas wykorzystywanych w programie. Wyciągnąć wnioski, dlaczego funkcje wirtualne są tak często używane.
 * - Zmienić program demonstrujący użycie klasy ListaOsob tak, by korzystał z klasy ListaOsob.
 */

#include <iostream>
#include <utility>
#include "Student.h"
#include "Czlowiek.h"

class ListaOsob {
private:
    Czlowiek **osoby;
    int liczbaOsob;
public:
    explicit ListaOsob(int liczba) {
        liczbaOsob = liczba;
        osoby = new Czlowiek *[liczbaOsob];
    };

    void dodaj(Czlowiek *czlowiek, int index) {
        if (index < 0 || index >= liczbaOsob) {
            std::cout << "Niepoprawny index" << std::endl;
            return;
        }
        if (osoby[index] != nullptr) {
            std::cout << "Miejsce jest juz zajete" << std::endl;
            return;
        }
        osoby[index] = czlowiek;
    };

    void wypiszDane(std::ostream &os) {
        for (int i = 0; i < liczbaOsob; i++) {
            if (osoby[i] != nullptr) {
                os << *osoby[i];
            }
        }
    };

    static void wypiszKlase(std::ostream &os) {
        os << "ListaOsob" << std::endl;
    };

    friend std::ostream &operator<<(std::ostream &os, ListaOsob &ListaOsob) {
        ListaOsob::wypiszKlase(os);
        ListaOsob.wypiszDane(os);
        return os;
    };

    ~ListaOsob() {
        for (int i = 0; i < liczbaOsob; i++) {
            delete osoby[i];
        }
        delete[] osoby;
    };
};

int main() {
    Czlowiek *person1 = Czlowiek::create();
    std::cout << *person1;
    std::cout << "\n";

    Czlowiek *person2 = Czlowiek::create();
    std::cout << *person2;
    std::cout << "\n";

    Czlowiek *person3 = Czlowiek::create();
    std::cout << *person3;
    std::cout << "\n";

    ListaOsob lista(3);
    lista.dodaj(person1, 0);
    lista.dodaj(person2, 2);
    lista.dodaj(person3, 1);

    std::cout << "\n";
    std::cout << lista;

    return EXIT_SUCCESS;
}

