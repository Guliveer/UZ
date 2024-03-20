#include <stdio.h>
#include <conio.h>
#include <string.h>

void main (){
    int i, a=3;
    char pw[a], pw_hash[a];

    printf("Podaj haslo: ");
    for (i = 0; i < a; i++){
        pw[i] = getch();
        printf("%c", pw[i]);
    }
    printf("\n");

    printf("Potwierdz haslo: ");
    for (i = 0; i < a; i++) {
        pw_hash[i] = getch();
        printf("*");
    }
    printf("\n");

    if ( !strcmp(pw, pw_hash) ){
        printf("Hasla sie zgadzajom");
    } else {
        printf("Hasla sa kurwa rozne");
    }
}
