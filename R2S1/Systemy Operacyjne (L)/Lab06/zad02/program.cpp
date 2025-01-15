#include <iostream>
using namespace std;

int dodaj(int a, int b) {
    return a + b;
}

int main() {
    int x = 5, y = 10;
    cout << "Suma: " << dodaj(x, y) << endl;
    return 0;
}
