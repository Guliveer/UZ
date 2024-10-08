//
// Created by Oliwer Pawelski on 12/06/2024.
//

#include "Czlowiek.h"

Czlowiek::Czlowiek(std::string name) {
    imie = std::move(name);
};

void Czlowiek::wypiszDane(std::ostream &os) {
    os << imie << std::endl;
};

void Czlowiek::wypiszKlase(std::ostream &os) {
    os << "Czlowiek" << std::endl;
};

std::ostream &operator<<(std::ostream &os, Czlowiek &czlowiek) {
    Czlowiek::wypiszKlase(os);
    czlowiek.wypiszDane(os);
    return os;
};

Czlowiek *Czlowiek::create() {
    std::string name;
    std::cout << "Podaj imie: ";
    std::cin >> name;
    return new Czlowiek(name);
};
