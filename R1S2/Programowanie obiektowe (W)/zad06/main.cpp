#include <iostream>

// Znajdź i popraw błędy (są 3)

class Figura {
protected:
    int x, y;
public:
    Figura(int _x = 1, int _y = 1) : x{_x}, y{_y} {}

    void rysuj() = 0;
    //? Funkcja powinna zostać zwirtualizowana (powód: klasa abstrakcyjna)
    //? virtual void rysuj() = 0;

    virtual void rysuj(int _x, int _y) = 0;
};

class Kwadrat : public Figura {
protected:
    int a;
public:
    Kwadrat(int _x = 1, int _y = 1, int _a = 1) : Figura(_x, _y), a{_a} {}

    void rysuj() override { std::cout << " rysuje Kwadrat"; }

    virtual void rysuj(unsigned int _x, unsigned int _y) override {//detale:
        std::cout << " rysuje Kwadrat";
    }
    //? Niepoprawne nadpisanie metody (powód: niezgodność argumentów)
    //? virtual void rysuj(int _x, int _y) {...}
};

Kwadrat k1(2, 3, 4);

void rysuj(Figura reff) {
    reff.rysuj();
}
//? Brak przekazania poprzez referencję (powód: sposób wywołania funkcji/metody)
//? void rysuj(Figura &reff) {...}

int main() {
    rysuj(k1);
}
