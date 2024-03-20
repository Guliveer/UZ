// Program wykonujący oblcizenia z wykorzystaniem funkcji Wilhelma Ackermanna

#include <stdio.h>

int ack(const int m, const int n) {
    if (m == 0)  return n + 1;
    if (n == 0)  return ack(m - 1, 1);
    return ack(m - 1, ack(m, n - 1));
}

int main() {
    int m, n;

    printf("= Funkcja Ackermanna =\n");
    printf("Podaj m: ");
    scanf("%d", &m);
    printf("Podaj n: ");
    scanf("%d", &n);

    printf("Wynik: %d\n", ack(m, n));
}
