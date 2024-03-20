// Program obliczający rekurencyjnie następujący ciąg liczb (n-liczba naturalna): xn=x(n−1) + x(n−2); gdzie x0=1; x1=1;
// Program powinien podawać elementy ciągu od zadanego n do zadanego m, oraz różnice pomiędzy kolej- nymi wyrazami ciągów.

#include <stdio.h>

int rekurencja(int n)
{
    if (n == 0)
        return 1;
    else if (n == 1)
        return 1;
    else
        return rekurencja(n - 1) + rekurencja(n - 2);
}

int main() {
    int n, m, i, wynik;
    printf("Podaj n: ");
    scanf("%d", &n);
    printf("Podaj m: ");
    scanf("%d", &m);
    for (i = n; i <= m; i++)
    {
        wynik = rekurencja(i);
        printf("%d ", wynik);
    }
    printf("\n");
    for (i = n; i < m; i++)
    {
        wynik = rekurencja(i + 1) - rekurencja(i);
        printf("%d ", wynik);
    }
    printf("\n");
    return 0;
}
