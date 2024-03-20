#include <stdio.h>
#include <conio.h>

void main() {
    int sum=0, x, i=0;

    while (sum <= 100) {
        printf("Podaj liczbe: ");
        scanf("%d", &x);
        sum += x;
        i++;
    }

    printf("Zsumowano %d liczb", i);
    return 0;
}
