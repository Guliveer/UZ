// Napisać program wykorzystując funkcję rekurencyjną wyświetlający poniższy rysunek. Funkcję musimy wywołać z parametrem oznaczającym wysokość piramidy.
/* np
 * 12345
 * 1234
 * 123
 * 12
 * 1
 */

#include <stdio.h>

void piramida(int n)
{
    if(n == 0)
        return;
    for(int i = 1; i <= n; i++)
        printf("%d", i);
    printf("\n");
    piramida(n-1);
}

int main()
{
    printf("Podaj n: ");
    int n;
    scanf("%d", &n);
    piramida(n);
    return 0;
}