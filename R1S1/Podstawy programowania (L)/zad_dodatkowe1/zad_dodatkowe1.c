#include <stdio.h>
#include <conio.h>
#include <math.h>

// Program licz¹cy potêge a^n liczby, z w³asn¹ funkcj¹ potêguj¹c¹

int power(int base, int n){
    int result=1;

    for(int i=1; i<=n; i++){
        result*=base; // result=base*base (zapêtlone) => 1 * base^n
    }

    return result; // zwróci wartoœæ 1, jeœli n<=0, bo pêtla nie wykona siê ani razu
}

void main() {
    // wypisanie potêg liczb 2 i (-3)
    int i, max_power=10, a=2, b=-3;

    printf("Potega:\t%6d\t%6d\n\n", a, b);
    for(i=0; i<=max_power; i++){
        printf("%6d\t%6d\t%6d\n", i, power(a,i), power(b,i));
    }
}
