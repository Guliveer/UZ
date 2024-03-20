#include <stdio.h>

int i = 1, a = 7, b = 7, j;

void main(void) {
    while (i < a) {
        j = i;

        do {
            printf("%d ", j);
            j += 2;
        } while (j < b + i);

        printf("\n");
        a -= 1;
    }
}
