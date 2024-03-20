#include <stdio.h>
#include <math.h>

void main() {
    float liczba;
    printf("Podaj liczbe: ");
    scanf("%f", &liczba);

    float o = pow(liczba, 4);
    printf("Podana liczba podniesiona do potegi trzeciej wynosi: %.2f", o);
}
