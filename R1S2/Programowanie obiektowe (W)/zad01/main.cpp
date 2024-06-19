#include <iostream>

class Punkt2D {
    int x, y;
public:
    Punkt2D(int x, int y) : x(x), y(y) {};

    Punkt2D() : x(1), y(1) {};

    int getX() const { return x; };

    int getY() const { return y; };

    void setX(int i) { x = i; };

    void setY(int i) { y = i; };

    virtual void out() { std::cout << x << y; }

    void in(int i, int j) {
        x = i;
        y = j;
    }
};

class Punkt3D : public Punkt2D {
    int z;
public:
    // Zdefiniuj konstruktor Punkt3D z parametrami
    Punkt3D(int x, int y, int z) : Punkt2D(x, y), z(z) {};

    // Zdefiniuj konstruktor Punkt3D domyślny
    Punkt3D() : Punkt2D(), z(1) {};

    // Zdefiniuj funkcję wirtualną "void out()", która wypisuje parametry 3D
    virtual void out() { std::cout << getX() << getY() << z; }
    // alternatywa:
    // void out() override {std::cout << getX() << getY() << z;}

    // Zdefiniuj funkcję "void in()", która ustawia dane określone przez
    // parametry do punktu 3D. WYkorzystaj finkcję in z klasy Punkt2D.
    void in(int i, int j, int k) {
        Punkt2D::in(i, j);
        z = k;
    }
};

int main() {
    // Zdefiniuj wskaźnik do klasy Punkt2D, obiekt klasy Punkt3D
    // i wywołaj funkcję out() z klasy Punkt3D za pomocą wskaźnika do klasy Punkt2D
    Punkt2D *p2d;
    Punkt3D p3d(1, 2, 3);
    p2d = &p3d;
    p2d->out(); // result: 123

    // alternatywa:
    // Punkt2D *p2d = new Punkt3D(1, 2, 3);
    // p2d->out(); // result: 123
    // delete p2d;

    return EXIT_SUCCESS;
}
