#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define row 3
#define col 3

void randomize(int T[][col], const int seed) {
    srand(seed);

    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            T[i][j] = rand() % 3;
        }
    }
}

void dispMatrixHor(const int T[row][col]) {
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            // dla każdej kolumny

            if (j != col - 1) {
                printf("%3d ", T[i][j]); // każdy element wiersza (poza ostatnim) pisany jest ze spacją na końcu
            }
            else {
                printf("%3d", T[i][j]); // ostatni wypisany wynik w wierszu nie zawiera spacji na końcu
            }
        }
        printf("\n");
    }
}

void dispMatrixVer(const int T[col][row]) {
    for (int i = 0; i < col; i++) {
        for (int j = 0; j < row; j++) {
            // dla każdej kolumny

            if (j != row - 1) {
                printf("%3d ", T[i][j]); // każdy element wiersza (poza ostatnim) pisany jest ze spacją na końcu
            }
            else {
                printf("%3d", T[i][j]); // ostatni wypisany wynik w wierszu nie zawiera spacji na końcu
            }
        }
        printf("\n");
    }
}

// void sumMatrix(int A[row][col], int B[row][col], int result[row][col]) {
//     for (int i = 0; i < row; i++) {
//         for (int j = 0; j < col; j++) {
//             result[i][j] = A[i][j] + B[i][j];
//         }
//     }
// }

void multipMatrix(const int A[row][col], const int B[col][row], int AB[row][row]) {
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < row; j++) {
            AB[i][j] = 0;
            for (int k = 0; k < col; k++) {
                AB[i][j] += A[i][k] * B[k][j];
            }
        }
    }
}


void transpMatrix(int A[][col]) {
    int temp[col][row];

    for (int i = 0; i < col; i++) {
        for (int j = 0; j < row; j++) {
            temp[i][j] = A[j][i];
        }
    }
    for (int i = 0; i < col; i++) {
        for (int j = 0; j < row; j++) {
            A[i][j] = temp[i][j];
        }
    }
}

int main() {
    // int A[row][col];
    // int B[col][row];
    // randomize(A, time(0));
    // randomize(B, rand());

    int A[row][col] = {{1, 2, 3}, {3, 4, 5}, {5, 6, 7}};
    int B[col][row] = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};

    dispMatrixHor(A);
    printf("\n");

    dispMatrixVer(B);
    printf("\n");

    int AB[row][row];
    multipMatrix(A, B, AB);

    printf("\n");

    printf("Wynik mnożenia macierzy A*B, to:\n");
    dispMatrixVer(AB);
}
