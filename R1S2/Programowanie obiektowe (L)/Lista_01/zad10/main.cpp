// Find min & max values in the array

#include <iostream>
using namespace std;

int main() {
    constexpr int arr[] = { 1, 2, 3, 4, 50, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
    int minVal = arr[0], maxVal = arr[0];

    cout << "Tablica:";
    for (const int i : arr) {
        cout << " " << i;
    }
    cout << "\n";

    for (const int i : arr) { // range-based loop: https://en.cppreference.com/w/cpp/language/range-for
        if (i < minVal) {
            minVal = i; // if i (current element) is less than minVal, assign i to minVal (new smallest value)
        }
        if (i > maxVal) {
            maxVal = i; // if i is greater than maxVal, assign i to maxVal (new largest value)
        }
    }

    // Output min & max values (in that order)
    cout << "Minimalna wartość: " << minVal << endl;
    cout << "Maksymalna wartość: " << maxVal << endl;
    return EXIT_SUCCESS;
}
