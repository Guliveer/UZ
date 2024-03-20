#include <stdio.h>

void main() {
    int d, m, d2, i;
    printf("Podaj miesiac: ");
    scanf("%d", &m);
    printf("Podaj dzien: ");
    scanf("%d", &d);

    for(i=1; i<m; i++){
        switch(i){
        case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            d2+=31;
            break;
        case 2:
            d2+=28;
            break;
        case 4: case 9: case 11:
            d2+=30;
            break;
        }
    }
    d2+=d;

    printf("Od poczatku roku uplynelo %d dni", d2);
}
