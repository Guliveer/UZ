// Wykorzystując notację wskaźnikową napisać samodzielnie funkcje (częściowo znane z ANSI C):
// - strlen(n) — wyznaczającą długość napisu n,
// - strcpy(ncel, nzrodlo) — kopiujacą napis nzrodlo na nsource. Jeśli napis źródłowy jest dłuższy od
//      docelowego, skopiować tyle, ile się zmieści.
// - strcmp2(n1, n2) — porównujacą dwa napisy (n1 i n2). Jeśli są równe — funkcja ma zwrócić zero, w
//      przeciwnym przypadku — jeden.
// - strchr(n, z) — sprawdzajacą, czy znak z występuje w napisie n. Jeśli tak, funkcja zwraca wskaźnik do
//      pierwszego znalezionego znaku z. Jeśli w napisie nie ma takiego znaku, funkcja zwraca wartość nullptr.
// - strrev(n) — odwracającą znaki w napisie n. Przykład działania: ”ala ma kota” → ”atok am ala”.

#include <iostream>
using namespace std;

// Each function has an underscore at the end of its name to avoid conflicts with the standard library functions.

int strlen_(const char *n) {
    int i = 0;
    while (n[i] != '\0') {
        i++;
    }
    return i;
}

void strcpy_(char *ncel, const char *nzrodlo) {
    int i = 0;
    while (nzrodlo[i] != '\0') {
        ncel[i] = nzrodlo[i];
        i++;
    }
    ncel[i] = '\0';
}

int strcmp_(const char *n1, const char *n2) {
    int i = 0;
    while (n1[i] != '\0' && n2[i] != '\0') {
        if (n1[i] != n2[i]) {
            return 1;
        }
        i++;
    }
    if (n1[i] == '\0' && n2[i] == '\0') {
        return 0;
    }
    return 1;
}

char *strchr_(const char *n, const char z) {
    int i = 0;
    while (n[i] != '\0') {
        if (n[i] == z) {
            return (char *) &n[i];
        }
        i++;
    }
    return nullptr;
}

void strrev_(char *n) {
    int i = 0;
    int j = strlen_(n) - 1;
    while (i < j) {
        char temp = n[i];
        n[i] = n[j];
        n[j] = temp;
        i++;
        j--;
    }
}

int main() {
    // Przykłady zasstosowań powyższych funkcji
    cout << "strlen(\"Ala ma kota.\") -> " << strlen_("Ala ma kota.") << endl;

    auto *a = new char; // dynamiczna alokacja pamięci dla napisu
    strcpy_(a, "kot");
    cout << "strcpy(a, \"kot\") -> a == " << a << endl;
    delete a; // zwolnienie pamięci

    cout << R"(strcmp("kot", "kot") -> )" << strcmp_("kot", "kot") << endl; // R"()" -> raw string literal

    cout << R"(strchr("kot", "o") -> )" << strchr_("kot", 'o') << endl;

    cout << "strrev(\"Ala ma kota.\") -> ";
    auto *b = new char;
    strcpy_(b, "Ala ma kota.");
    strrev_(b);
    cout << b << endl;
    delete b;


    return EXIT_SUCCESS;
}
