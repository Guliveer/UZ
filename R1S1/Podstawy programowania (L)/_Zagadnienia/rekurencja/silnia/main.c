// Przykład funkcji rekurencyjnej (tu: silnia)

#include <stdio.h>

int factorial(const int x) {
    /* Definicja rekurencyjna (deklaratywna)
     *
     * n! = { n * (n-1)! dla x > 0
     *      { 1 dla x = 0
     */

    // Funkcja rekurencyjna (wywołująca samą siebie)
    if (x == 0) return 1;
    return x * factorial(x - 1); // rekurencja
}

void main() {
    printf("Podaj liczbe do obliczenia silni: ");
    int x;
    scanf("%d", &x);
    printf("%d! = %d\n", x, factorial(x));
}
