#include <stdio.h> // dołącz bibliotekê stdio.h
#include <conio.h> // dołącz bibliotekê conio.h

void main() {
    int liczba; // deklaracja zmiennej typu integer
    printf("To jest drugi program.\n"); // wyœwietl tekst na ekranie
    printf("Podaj liczbe: "); // wyœwietl tekst na ekranie
    scanf("%d", &liczba); // wczytaj wprowadzone znaki jako liczbê i zapisz w zmiennej liczba

    printf("Podana liczba: %d", liczba); // wyœwietl tekst na ekranie

    printf("\nDowolny klawisz zamyka okno...");
    getch(); // czeka na naciœniêcie dowolnego klawisza
}
