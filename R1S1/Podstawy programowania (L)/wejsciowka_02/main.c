#include <stdio.h>

int factorial(int x){
    int result=1;
    for (int i=1; i<=x; i++){
        result*=i;
    }
    return result;
}

int main() {
    for(int i=0; i<=17; i++){
        printf("%d! = %d\n", i, factorial(i));
    }
}
