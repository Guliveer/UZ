#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define wier 3
#define kol 5

// https://www.scaler.com/topics/random-number-generator-in-c/

void randomize(int T[][kol]) {
    srand(time(0));
    /* srand(), bo samo rand() nie jest tak naprawdę losowe,
     * więc ustawiamy ziarno (seed) funkcji rand() (stąd "srand()") na wartość czasu
     */

    for (int i = 0; i < wier; i++) {
        for (int j = 0; j < kol; j++) {
            do {
                T[i][j] = rand() % 1000;
            }
            while (T[i][j] % 2 != 0); // generuje tylko liczby parzyste
        }
    }
}

void disp(int T[][kol]) {
    for (int i = 0; i < wier; i++) {
        // dla każdego wiersza (i - index, R - ilość indeksów; stąd i<R)

        for (int j = 0; j < kol; j++) {
            // dla każdej kolumny

            if (j != kol - 1) {
                printf("%3d ", T[i][j]); // każdy element wiersza (poza ostatnim) pisany jest ze spacją na końcu
            }
            else {
                printf("%3d", T[i][j]); // ostatni wypisany wynik w wierszu nie zawiera spacji na końcu
            }
        }

        printf("\n");
    }
}

int main() {
    int T[wier][kol];
    randomize(T);
    disp(T);
}
