#include <stdio.h>
#include <conio.h>
#include <string.h>

void main() {
    int input;
    printf("Podaj liczbe: ");
    scanf("%d", &input);

    printf("Ta liczba w systemie szestnastkowym to: %X", input);
}
