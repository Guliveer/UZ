#include <iostream>

class Ulamek {
    int l, m;

public:
    Ulamek(int l = 1, int m = 1) : l(l), m(m) {}

    // Zdefiniować funkcję konwertującą która zagwarantuje poprawność instrukcji:
    // Ulamek u1;
    // int a = u1;
    operator int() const { return l / m; }

    // Zdefiniować funkcję operatorową strumienia wyjściowego "operator<<"
    // (np. cout<<u1;)
    friend std::ostream &operator<<(std::ostream &os, const Ulamek &u) {
        os << u.l << "/" << u.m; // result: "{l}/{m}"
        return os;
    }
    // alternatywa:
    // std::ostream &operator<<(std::ostream &os) const {
    //     os << l << "/" << m; // result: l/m = ?
    //     return os;
    // }
};

int main() {
    Ulamek u1(3, 2);
    int a = u1;
    std::cout << a << std::endl; // result: 3/2 -> 1.5 -> 1

    Ulamek u2(5);
    int b = u2;
    std::cout << b << std::endl; // result: 5/1 -> 5

    Ulamek u3(4, 2);
    std::cout << int(u3) << std::endl; // result: 4/2 -> 2

    Ulamek u4(1, 2);
    std::cout << (int) u4 << std::endl; // result: 1/2 -> 0.5 -> 0

    Ulamek u5(12, 6);
    std::cout << u5 << std::endl; // result: 12/6

    return EXIT_SUCCESS;
}
