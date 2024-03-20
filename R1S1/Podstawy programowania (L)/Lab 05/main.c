// program tworzący tablicę dwuwymiarową z dynamicznym przypisaniem pamięci, wypełniona wartościami losowymi dwucyfrowymi i odnaleźć wartość największą
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void breakLine() {
    printf("\n");
}

void fillArray(int* arr, const int r, const int c) {
    srand(time(NULL));
    for (int i = 0; i < r; i++) {
        // poruszanie się po wierszach
        for (int j = 0; j < c; j++) {
            // poruszanie się po kolumnach
            do {
                *(arr + i * c + j) = rand() % 100; // losowanie wartości
            }
            while (*(arr + i * c + j) < 10);
            // żeby były dwucyfrowe - jeśli wartość <10 (jednocyfrowa) to losuj ponownie
        }
    }
}

void printArray(const int* arr, const int r, const int c) {
    for (int i = 0; i < r; i++) {
        // poruszanie się po wierszach
        printf("\n"); // nowa linia
        for (int j = 0; j < c; j++) {
            // poruszanie się po kolumnach
            if (j == c - 1) {
                // sprawdź czy aktualnie operowana kolumna jest ostatnia w wierszu
                // jeśli tak, to nie dodawaj spacji po wartości
                printf("%d", *(arr + i * c + j));
            }
            else {
                // jeśli nie, to dodaj spacje po wartości
                printf("%d ", *(arr + i * c + j));
            }
        }
    }
    printf("\n");
}

void findMax(const int* arr, const int r, const int c) {
    int max = *arr; // przypisanie wartości pierwszej komórki tablicy do zmiennej max
    for (int i = 0; i < r; i++) {
        // poruszanie się po wierszach
        for (int j = 0; j < c; j++) {
            // poruszanie się po kolumnach
            if (*(arr + i * c + j) > max) {
                // jeśli wartość w tablicy jest większa od aktualnej wartości max
                max = *(arr + i * c + j); // przypisz nową wartość max
            }
        }
    }
    // po zakonczeniu wyszukowania wartości max, wypisz ją
    printf("Max: %d\n", max);
}

void findMin(const int* arr, const int r, const int c) {
    int min = *arr; // przypisanie wartości pierwszej komórki tablicy do zmiennej min
    for (int i = 0; i < r; i++) {
        // poruszanie się po wierszach
        for (int j = 0; j < c; j++) {
            // poruszanie się po kolumnach
            if (*(arr + i * c + j) < min) {
                // jeśli wartość w tablicy jest mniejsza od aktualnej wartości min
                min = *(arr + i * c + j); // przypisz nową wartość min
            }
        }
    }
    // po zakonczeniu wyszukowania wartości min, wypisz ją
    printf("Min: %d\n", min);
}

int* transposeMatrix(int* arr, const int r, const int c) {
    int* arr2 = malloc(r * c * sizeof(int)); // alokacja pamięci dla tablicy (tworzenie tablicy)

    for (int i = 0; i < r; i++) {
        // poruszanie się po wierszach
        for (int j = 0; j < c; j++) {
            // poruszanie się po kolumnach
            *(arr2 + j * r + i) = *(arr + i * c + j);
        }
    }

    return arr2;
}

int main() {
    const int r = 3, c = 4; // deklaracja liczba wierszy i kolumn
    int* arr = malloc(r * c * sizeof(int)); // alokacja pamięci dla tablicy (tworzenie tablicy)

    fillArray(arr, r, c);
    printArray(arr, r, c);

    breakLine(); // estetyczny odstęp

    findMax(arr, r, c);

    int* arrTrans = transposeMatrix(arr, r, c);
    //! jeśli nie przypiszesz transpozycji do zmiennej, która jest potem zwalniana, to jest wyciek pamięci
    printArray(arrTrans, c, r);

    // zwolnienie pamięci zajmowanej przez tablice
    free(arr), free(arrTrans);
}
