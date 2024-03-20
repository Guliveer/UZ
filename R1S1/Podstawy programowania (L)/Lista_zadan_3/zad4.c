#include <stdio.h>

void main() {
    float a, b, c;
    printf("Podaj bok a: ");
    scanf("%f", &a);
    printf("Podaj bok b: ");
    scanf("%f", &b);
    printf("Podaj bok c: ");
    scanf("%f", &c);

    if ( (a+b) <= c || (a+c) <= b || (b+c) <= a ){
        printf("Trojkat o podanych bokach nie istnieje");
    } else {
        printf("Trojkat o podanych bokach moze istniec");
    }
    printf("\n");
}
