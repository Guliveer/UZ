// Napisać deklaracje następujących wskaźników do funkcji. Napisać program demonstrujący użycie tych
// wskaźników. W programie napisać dwie funkcje pasujące do zadanego wskaźnika. Wybór pomiędzy wywołaniem
// jednej z funkcji pozostawić użytkownikowi. Wywołanie każdej funkcji ma nastąpić przy pomocy wskaźnika.
// - wskaźnik do funkcji, która nic nie zwraca i przyjmuje jeden argument — liczbę całkowitą.
// - wskaźnik do funkcji, zwraca liczbę zmiennoprzecinkową i przyjmuje dwa argumenty — liczbę całkowitą i liczbę zmiennoprzecinkową.
// - wskaźnik do funkcji, zwraca liczbę całkowitą i przyjmuje jeden argument — tablicę trzech znaków

#include <iostream>
using namespace std;

void fun1(const int a) {
    cout << "fun1: " << a << endl;
}

float fun2(const int a, const float b) {
    cout << "fun2: " << a << " " << b << endl;
    return a + b;
}

int fun3(const char a[3]) {
    cout << "fun3: " << a << endl;
    return 0;
}

int main() {
    cout << "Wybierz funkcje do wywolania:" << endl;
    cout << "1. nic nie zwraca; 1 arg. - liczba calkowita" << endl;
    cout << "2. zwraca liczbe zmiennoprzecinkowa; 2 arg. - liczba calkowita i zmiennoprzecinkowa" << endl;
    cout << "3. zwraca liczbe calkowita; 1 arg. - tablica trzech znakow" << endl;
    cout << "\nWybor: ";
    int choice;
    cin >> choice;
    cout << "\n";

    switch (choice) {
        case 1: {
            cout << "Podaj liczbe calkowita: ";
            int a;
            cin >> a;
            void (*fun1_ptr)(const int) = fun1;
            fun1_ptr(a);
            break;
        }
        case 2: {
            cout << "Podaj liczbe calkowita i zmiennoprzecinkowa: ";
            int a;
            float b;
            cin >> a >> b;
            float (*fun2_ptr)(const int, const float) = fun2;
            cout << fun2_ptr(a, b) << endl;
            break;
        }
        case 3: {
            cout << "Podaj trzy znaki: ";
            char a[3];
            cin >> a;
            int (*fun3_ptr)(const char[3]) = fun3;
            fun3_ptr(a);
            break;
        }
        default: {
            cout << "Niepoprawny wybor!\n\n" << endl;
            main();
            break;
        }
    }
    return EXIT_SUCCESS;
}
