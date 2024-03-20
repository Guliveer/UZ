#include <stdio.h>
#define N 10
int tab[N]={10,0,7,1,3,4,5,8,2,6};

void disp(int arr[], const int size) {
    for (int i=0; i<size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

void sort_reg(int arr[], const int size) {
    for (int i=0; i<size; i++) {
        for (int j=size-1; j>i; j--) {
            if (arr[j] < arr[j-1]) {
                const int tmp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = tmp;
            }
        }
    }
}

void sort_prog(int arr[], const int size) {
    for (int i=0; i<size; i++) {
        for (int j=i+1; j<size; j++) {
            if (arr[j] < arr[i]) {
                const int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}

void reverse(int arr[], const int size) {
    for (int start = 0, end = size - 1; start < end; start++, end--) {
        const int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }
}

int main() {
    disp(tab, N);
    sort_reg(tab, N);
    disp(tab, N);
    reverse(tab, N);
    disp(tab, N);
}
