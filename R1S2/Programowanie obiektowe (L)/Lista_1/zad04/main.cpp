#include <iostream>
using namespace std;

int zwiekszacz (const int x) {
    return x + 1;
}

int main() {
    cout << "Podaj liczbe: ";
    int x;
    cin >> x;
    cout << zwiekszacz(x) << endl;
    return 0;
}
