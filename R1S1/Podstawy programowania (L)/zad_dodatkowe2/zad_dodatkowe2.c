#include <stdio.h>
#include <math.h>

float power(float base, int n){
    float result=1;

    for(int i=1; i<=n; i++){
        result*=base; // result=base*base (zapêtlone) => 1 * base^n
    }

    return result; // zwróci wartoœæ 1, jeœli n<=0, bo pêtla nie wykona siê ani razu
}

float factorial(int n){
    float result=1;
    for(int i=1; i<=n; i++){
        result*=i;
    }
    return result;
}

float sinus(float x){
    float result=0;
    for (int i=1; i<=10; i+=4) {
        result+=(power(x, i)/factorial(i) - power(x, i+2)/factorial(i+2));
    }
    return result;
}

void main(){
    float x=0.75;
    printf("%f", sinus(x));
}
