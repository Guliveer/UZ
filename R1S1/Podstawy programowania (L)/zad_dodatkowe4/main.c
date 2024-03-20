// Program sortujący i wypisujący ciągi znaków

#include <stdio.h>
#include <string.h>

void disp(char *TM[], const int LM) {
    for (int i=0; i<LM; i++) {
        printf("%s ", TM[i]);
    }
}

void sort(char *TM[], const int LM) {
    for (int i=0; i<LM; i++) { // pętla dla porównania wszystkich elementów
        for (int j=0; j<LM-1; j++) { // pętla dla porównania dwóch sąsiednich elementów
            if (strcmp(TM[j], TM[j+1])>0) { // porównanie ciągów znaków => strcmp(string1, string2) => >0 gdy string1>string2
                // posortowanie elementów
                char *tmp=TM[j];
                TM[j]=TM[j+1];
                TM[j+1]=tmp;
            }
        }
    }
}

char *max_val(char *TM[], const int LM) {
    char *max=TM[0]; // przypisanie pierwszej wartości z tablicy do zmiennej max
    for (int i=1; i<LM; i++) { // pętla od 1, bo 0 jest już w max, do LM
        if (strcmp(TM[i], max)>0) { // porównanie ciągów znaków => strcmp(string1, string2) => >0 gdy string1>string2
            max=TM[i]; // przypisanie nowej wartości maksymalnej
        }
    }
    return max; // zwrócenie wartości maksymalnej
}

int main() {
    char *TM[]={"sty","lut","mar","kwi","maj","cze","lip","sie","wrz","paz","lis","gru"};
    const int LM=12;

    disp(TM, LM);
    printf("\nThe biggest value of array is: %s", max_val(TM, LM));
    sort(TM, LM), printf("\n"), disp(TM, LM);
}
