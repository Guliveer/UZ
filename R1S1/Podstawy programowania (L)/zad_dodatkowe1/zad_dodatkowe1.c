#include <stdio.h>
#include <conio.h>
#include <math.h>

// Program licz�cy pot�ge a^n liczby, z w�asn� funkcj� pot�guj�c�

int power(int base, int n){
    int result=1;

    for(int i=1; i<=n; i++){
        result*=base; // result=base*base (zap�tlone) => 1 * base^n
    }

    return result; // zwr�ci warto�� 1, je�li n<=0, bo p�tla nie wykona si� ani razu
}

void main() {
    // wypisanie pot�g liczb 2 i (-3)
    int i, max_power=10, a=2, b=-3;

    printf("Potega:\t%6d\t%6d\n\n", a, b);
    for(i=0; i<=max_power; i++){
        printf("%6d\t%6d\t%6d\n", i, power(a,i), power(b,i));
    }
}
