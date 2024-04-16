// Wstaw podany wektor m liczb całkowitych pomiędzy każde dwa kolejne wiersze w dwuwymiarowej tablicy
// liczb całkowitych m × n (z uprzednim przesunięciem zawartości tablicy poniżej miejsca wstawienia).
// Przykład:
// m = 4, n = 2, m = [10, 20]:
// Jeśli tablica ma tylko jeden wiersz, wstawić wektor m po nim.

#include <iostream>
using namespace std;

int main() {
    constexpr int m=4,n=2;
    constexpr int w[n]={10,20};
    constexpr int tab[m][n]={{1,2},{3,4},{5,6},{7,8}};
    int x, k=0;

    if constexpr (m==1) {
        x = m * 2;
    } else {
        x = m * 2 - 1;
    }

    int tab2[x][n];

    for (int i=0; i<x; i++) {
        //! CHUJ WIE CZEMU TO DZIAŁA ALE NIE DOTYKAĆ, BO SIĘ ZEPSUJE
        //! ENG: FUCK KNOWS HOW THIS SHIT WORKS. DON'T TOUCH OR ELSE IT EXPLODES

        for (int j=0; j<n; j++) {
            if (i%2==0) {
                tab2[i][j]=tab[k][j];
            } else {
                tab2[i][j]=w[j];
            }
        }

        if (i%2==0) {
            k++;
        }
    }

    cout << "Tablica po wstawieniu wektora m: " << endl;
    for (auto & i : tab2) {
        for (int j : i) {
            cout << j << " ";
        }
        cout << endl;
    }

    return EXIT_SUCCESS;
}
