// THIS IS JUST AN EXAMPLE, NOT A FUNCTIONAL PROGRAM
#include <iostream>

class X {
public:
    f1(void);

    virtual f2(void);
} x, *px;

class Y : public X {
public:
    f1(void);

    virtual f2(void);
} y, *py;

int main() {
    py = &y;    // A
    py->f1();   // B
    px = &y;    // C
    px->f2();   // D

    // Z których klas zostaną wywołane funkcje w powyższym fragmencie programu?
    // Wybór uzasadnij.

    // [A] Y::f1(), bo py jest wskaźnikiem na obiekt klasy Y
    // [B] Y::f1(), bo py jest wskaźnikiem na obiekt klasy Y
    // [C] X::f2(), bo px jest wskaźnikiem na obiekt klasy Y, ale wskaźnik jest typu X
    // [D] Y::f2(), bo px jest wskaźnikiem na obiekt klasy Y, a f2 jest funkcją wirtualną

    return EXIT_SUCCESS;
}
