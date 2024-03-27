// Co oznaczają następujące deklaracje?
// - char (*r)[];
// - char *r[10];
// - char q(char *);
// - char (*q)(char[]);

#include <iostream>
using namespace std;

int main() {
    cout << "char (*r)[] - deklaracja wskaźnika na tablicę znaków" << endl;
    cout << "char *r[10] - deklaracja tablicy wskaźników na znaki" << endl;
    cout << "char q(char *) - deklaracja funkcji zwracającej znak i przyjmującej wskaźnik na znak" << endl;
    cout << "char (*q)(char[]) - deklaracja wskaźnika na funkcję zwracającej znak i przyjmującej tablicę znaków" << endl;

    return EXIT_SUCCESS;
}
