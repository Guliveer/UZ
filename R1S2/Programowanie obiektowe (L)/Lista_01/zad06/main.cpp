#include <iostream>
using namespace std;

double dist(const int p1[3], const int p2[3]) {
    return sqrt(pow((p1[0] - p2[0]),2) + pow((p1[1] - p2[1]), 2) + pow((p1[2] - p2[2]), 2));
}

int main() {
    int p1[3], p2[3];
    cout << "Podaj wspolrzedne pierwszego punktu (x y z): ";
    cin >> p1[0] >> p1[1] >> p1[2];
    cout << "Podaj wspolrzedne drugiego punktu (x y z): ";
    cin >> p2[0] >> p2[1] >> p2[2];
    cout << "\nOdległość między tymi punktami wynosi: " << dist(p1, p2) << endl;
    return 0;
}
