#include <iostream>
using namespace std;

void triangle() {
    for (int i = 1; i <= 9; i++) {
        for (int j = 1; j <= i; j++) {
            cout << i;
        }
        cout << endl;
    }
}

int main() {
    triangle();
    return 0;
}
