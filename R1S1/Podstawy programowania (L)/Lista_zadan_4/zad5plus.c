// Opracuj algorytm (schemat blokowy) i napisz program zwracaj¹cy najwiêksz¹ z w chuj liczb podanych przez u¿ytkownika.

#include <stdio.h>

void main()
{
    int n;
    double arr[100];
    printf("Wprowadz liczbe elementow (1 to 100): ");
    scanf("%d", &n);

    for (int i = 0; i < n; ++i)
    {
        printf("Wprowadz liczbe %d: ", i + 1);
        scanf("%lf", &arr[i]);
    }

    for (int i = 1; i < n; ++i)
    {
        if (arr[0] < arr[i])
        {
            arr[0] = arr[i];
        }
    }

    printf("Najwiekszy element to %lf", arr[0]);

    return 0;
}
