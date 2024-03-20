#include <iostream>
using namespace std;

int zwiekszacz(const int x) {
    return x + 1;
}

int main() {
    for (int i = 0; i < 18; i++) {
        cout << "Argument: " << i << ", zwiekszacz(" << i << ") = " << zwiekszacz(i) << endl;
    }
    return 0;
}
