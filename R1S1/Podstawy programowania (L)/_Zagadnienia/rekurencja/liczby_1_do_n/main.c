#include <stdio.h>

void wypisz(int n) {
    if (n > 1) {
        wypisz(n - 1);
    }
    printf("%d\n", n);
}

int main() {
    int n;
    printf("Podaj n: ");
    scanf("%d", &n);
    wypisz(n);
    return 0;
}
