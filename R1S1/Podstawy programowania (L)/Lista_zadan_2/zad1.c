#include <stdio.h>
#include <conio.h>

void main() {
    char name[30], surname[30];
    int age;

    printf("Podaj swoje imie: ");
    scanf("%s", name);

    printf("Podaj swoje nazwisko: ");
    scanf("%s", surname);

    printf("Podaj swoj wiek: ");
    scanf("%d", &age);

    printf("\nWitaj %s %s! Masz %d lat.\n", name, surname, age);
}
