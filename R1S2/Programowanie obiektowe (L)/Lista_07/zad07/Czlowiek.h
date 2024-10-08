//
// Created by Oliwer Pawelski on 12/06/2024.
//

#ifndef ZAD07_CZLOWIEK_H
#define ZAD07_CZLOWIEK_H

#include <iostream>
#include <string>
#include <utility>

class Czlowiek {
private:
    std::string imie;

public:
    explicit Czlowiek(std::string name);

    virtual void wypiszDane(std::ostream &os);

    static void wypiszKlase(std::ostream &os);

    friend std::ostream &operator<<(std::ostream &os, Czlowiek &czlowiek);

    static Czlowiek *create();

    virtual ~Czlowiek() = default;
};


#endif //ZAD07_CZLOWIEK_H
