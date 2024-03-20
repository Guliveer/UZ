#include <iostream>
using namespace std;

void findMinMax(const int arr[], const int size, int* min, int* max) {
    int minVal = arr[0], maxVal = arr[0];
    for (int i = 0; i < size; i++) {
        if (arr[i] < minVal) {
            minVal = arr[i];
        }
        else if (arr[i] > maxVal) {
            maxVal = arr[i];
        }
    }
    *min = minVal, *max = maxVal;
}

int main() {
    int minVal, maxVal;
    constexpr int arr[] = { 1, 2, 3, 4, 50, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };

    cout << "Tablica:";
    for (const int i : arr) {
        cout << " " << i;
    }
    cout << endl;

    findMinMax(arr, size(arr), &minVal, &maxVal);
    cout << "Minimalna wartość: " << minVal << endl;
    cout << "Maksymalna wartość: " << maxVal << endl;
    return 0;
}
