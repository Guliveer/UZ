// Opracuj algorytm (schemat blokowy) i napisz program zwracaj¹cy najwiêksz¹ z trzech liczb podanych przez u¿ytkownika.

#include <stdio.h>

void main() {
    int a, b, c;
    printf("Podaj pierwsza liczbe: ");
    scanf("%d", &a);
    printf("Podaj druga liczbe: ");
    scanf("%d", &b);
    printf("Podaj trzecia liczbe: ");
    scanf("%d", &c);

    if (a > b && a > c) {
        printf("Najwieksza liczba jest: %d", a);
    } else if (b > a && b > c) {
        printf("Najwieksza liczba jest: %d", b);
    } else if (c > a && c > b) {
        printf("Najwieksza liczba jest: %d", c);
    } else if (a == b && b == c) {
        printf("Wszystkie liczby sa rowne");
    } else {
        printf("Unexpected error occured.");
    }
}
