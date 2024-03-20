#include <stdio.h>
#include <conio.h>
#include <math.h>

void main() {
    float promien;
    printf("Podaj dlugosc promienia: ");
    scanf("%f", &promien);

    float o = 2*3.1412*promien, p = 3.1412*(promien*promien);
    printf("Obwod kola wynosi: %.2f\n", o);
    printf("Pole kola wynosi: %.2f", p);

}
