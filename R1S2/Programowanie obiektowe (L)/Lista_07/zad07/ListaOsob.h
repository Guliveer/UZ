//
// Created by Oliwer Pawelski on 12/06/2024.
//

#ifndef ZAD07_LISTAOSOB_H
#define ZAD07_LISTAOSOB_H

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


#endif //ZAD07_LISTAOSOB_H
