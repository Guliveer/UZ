#include <iostream>
using namespace std;

float maxValue(const float a, const float b, const float c) {
    if (a > b && a > c) return a;
    if (b > a && b > c) return b;
    if (c > a && c > b) return c;

    return 0;
}

int main() {
    cout << maxValue(1,6,4.22) << endl;
    return 0;
}
