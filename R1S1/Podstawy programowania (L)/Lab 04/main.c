// Algorytm wyszukiwania wzorca w tescie (naiwny)

#include <stdio.h>
#include <string.h>

void bruteforce_find(char *pattern, char *text) {
    int i, j, k;
    int n = strlen(text);
    int m = strlen(pattern);
    for (i = 0; i <= n - m; i++) {
        k = i, j = 0;
        while (j < m && text[k] == pattern[j]) {
            k++, j++;
        }
        if (j == m) {
            printf("Wzorzec wystepuje juz od pozycji %d (index %d)\n", i+1, i);
            return;
        }
    }
    printf("Wzorzec nie wystepuje w tekscie\n");
}

int main() {
    printf("Nie uzywaj znakow diakrytycznych!\n\n");
    printf("Podaj tekst: ");
    char text[100];
    scanf(" %[^\n]", &text);
    printf("Podaj wzorzec: ");
    char pattern[100];
    scanf(" %[^\n]", &pattern);

    bruteforce_find(pattern, text);
}
