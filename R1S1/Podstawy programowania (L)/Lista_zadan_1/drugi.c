#include <stdio.h>
/* wypisuje zestawienie temperatur
Fahrenheita-Celsjusza; f = 0, 20, ..., 300 */

#define lower 10
#define upper 300
#define step 15

int main() {
    int fahr, celsius;

    printf("[ Fahrenheit > Celsjusz ]\n\n");

    if (upper < lower) {
        printf("Ty jebany debilu");
    }

    for (fahr=lower; fahr <= upper; fahr+=step) {
        celsius = 5*(fahr-32)/9;
        printf("%6d\t>> %6d\n", fahr, celsius);
    }
}
