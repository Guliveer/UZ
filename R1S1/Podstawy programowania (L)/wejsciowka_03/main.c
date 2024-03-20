#include <stdio.h>

int max_val(int arr[], const int N) {
    int max = arr[0], max_i = 0;

    for (int i = 0; i <= N; i++) {
        if (arr[i] > max) {
            max = arr[i];
            max_i = i;
        }
    }
    return max_i;
}


int main() {
    const int n = 6, tab[n] = {4, -5, 10, -666, 124, 0};
    printf("Index of the biggest value is: %d", max_val(tab, n));
}
