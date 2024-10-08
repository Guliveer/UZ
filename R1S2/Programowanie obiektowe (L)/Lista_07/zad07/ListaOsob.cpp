//
// Created by Oliwer Pawelski on 12/06/2024.
//

#include "ListaOsob.h"
#include "Czlowiek.h"
#include <iostream>
#include <utility>

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