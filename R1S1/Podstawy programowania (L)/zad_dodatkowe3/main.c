#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void sort(char s[]) {
    int i, j;
    char c;
    int R = strlen(s);
    for (int i = 0; i < R; i++) {
        for (int j = i; j > 0; j--) {
            if (s[j] < s[j - 1]) {
                c = s[j];
                s[j] = s[j - 1];
                s[j - 1] = c;
            }
        }
    }
}

int main() {
    char n[] = "sortowanie";
    printf("Napis nieposortowany: %s\n", n);
    sort(n);
    printf("Napis posortowany: %s\n", n);
}
