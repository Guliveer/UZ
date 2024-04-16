//  Znajdź min i max z tablicy dwuwymiarowej
#include <iostream>

using namespace std;

int main() {
    constexpr double arr[][2]={{10,2},{3,4},{5,6},{7,8},{90,10},{11,12},{13,14},{15,16}};
    int min=arr[0][0], max=arr[0][0];

    for (auto i : arr) {
        for (auto j : {0,1}) {
            if (i[j]<min) min=i[j];
            if (i[j]>max) max=i[j];
        }
    }

    cout << "Min: " << min << "\nMax: " << max;
    return EXIT_SUCCESS;
}
