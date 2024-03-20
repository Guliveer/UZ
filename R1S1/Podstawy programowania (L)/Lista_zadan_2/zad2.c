#include <stdio.h>
#include <conio.h>
#include <math.h>

void main() {
    float bok1, bok2, bok3;
    printf("Podaj dlugosc pierwszego boku: ");
    scanf("%f", &bok1);

    printf("Podaj dlugosc drugiego boku: ");
    scanf("%f", &bok2);

    printf("Podaj dlugosc trzeciego boku: ");
    scanf("%f", &bok3);

    float p = (bok1 + bok2 + bok3)/2, s = sqrt(p*(p-bok1)*(p-bok2)*(p-bok3));

    printf("Pole trojkata o podanych bokach wynosi: %.2f", s);
}
