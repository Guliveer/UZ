// Oblicz wartość sumy następujących szeregów, dla x spełniającego −π <= x <= π.
// Szeregi podano w postaci ogólnej S = a1 + a2 + a3 + ...
// S1 = x − x^3/3! + x^5/5! − x^7/7! + ...
// S2 = 1 - x^2/2! − x^4/4! + x^6/6! − x^8/8! + ...
// Obliczenia sumy szeregów prowadzić tak długo, aż wartość bezwzględna elementu ai będzie mniejsza od
// ε = 0,001. Ile wynoszą wartości S1 i S2 dla x = 0, x = π/4, x = π/2, x = π?
// Wskazówka: policzyć na kalkulatorze wartość (√2)/2

#include <iostream>
#include <cmath>
using namespace std;

float epsilon = 0.001; // globalna zmienna 'epsilon'

double factorial(const int n) { // funkcja obliczająca silnię
    double result = 1;
    for(int i = 1; i <= n; ++i) {
        result *= i;
    }
    return result;
}

double S1(const double x) { // funkcja obliczająca sumę szeregu S1
    double S1 = 0, ai = x;
    int n = 1;
    while(abs(ai) >= epsilon) {
        S1 += ai;
        ai = pow(-1, n) * pow(x, 2 * n + 1) / factorial(2 * n + 1);
        n++;
    }
    return S1;
}

double S2(const double x) { // funkcja obliczająca sumę szeregu S2
    double S2 = 0, ai = 1;
    int n = 1;
    while(abs(ai) >= epsilon) {
        S2 += ai;
        ai = pow(-1, n) * pow(x, 2 * n) / factorial(2 * n);
        n++;
    }
    return S2;
}

int main() {
    cout << "S1(0) = " << S1(0) << "\n";
    cout << "S2(0) = " << S2(0) << "\n\n";
    cout << "S1(π/4) = " << S1(M_PI / 4) << "\n";
    cout << "S2(π/4) = " << S2(M_PI / 4) << "\n\n";
    cout << "S1(π/2) = " << S1(M_PI / 2) << "\n";
    cout << "S2(π/2) = " << S2(M_PI / 2) << "\n\n";
    cout << "S1(π) = " << S1(M_PI) << "\n";
    cout << "S2(π) = " << S2(M_PI) << "\n";

    return EXIT_SUCCESS;
}
