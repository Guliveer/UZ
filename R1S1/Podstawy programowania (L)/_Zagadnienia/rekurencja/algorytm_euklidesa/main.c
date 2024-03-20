/*
    * Wiedząc, że największy wspólny dzielnik dwóch liczb a i b może być wyznaczony przy użyciu następującej funkcji (algorytm Euklidesa) zapisanej w pseudokodzie
    *
    * FUNKCJA Euklides(n, m)
    *  JEŚLI m = 0 TO Euklides=n;
    *  W PRZECIWNYM WYPADKU: Euklides(m, n mod m );
    * end
    *
    * należy napisać funkcję realizującą algorytm Euklidesa i sprawdzić jego działanie dla następujących par liczb: a = 289, b = 850; a = 17, b = 72; a = 170, b = 850; a = 2890, b = 850.
*/

#include <stdio.h>

int euklides(const int n, const int m) {
    if (m == 0) {
        return n;
    } else {
        return euklides(m, n % m);
    }
}

void main() {
    printf("= Algorytm Euklidesa =\n");
    printf("Podaj n: ");
    int n;
    scanf("%d", &n);

    printf("Podaj m: ");
    int m;
    scanf("%d", &m);

    printf("NWD(%d, %d) = %d\n", n, m, euklides(n, m));
}