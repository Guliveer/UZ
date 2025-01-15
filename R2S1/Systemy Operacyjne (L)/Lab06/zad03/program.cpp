#include <iostream>
using namespace std;

void funkcja1() {
    for (int i = 0; i < 100000; ++i);
}

void funkcja2() {
    for (int i = 0; i < 200000; ++i);
}

int main() {
    for (int i = 0; i < 1000; ++i) {
        funkcja1();
        funkcja2();
    }
    return 0;
}
