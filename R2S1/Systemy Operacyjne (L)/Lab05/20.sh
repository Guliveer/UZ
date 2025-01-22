# Za pomocą polecenia `awk` wyznaczyć średnie ocen punktowych studentów z kolokwiów. Dane zapisane są w pliku tekstowym i mają strukturę:
# 
# student kolokwium1 kolokwium2 ... kolokwium
# 1 85 67 ... 76
# 2 67 56 ... 89
# .
# .
# .
# m 45 78 ... 99

#!/bin/bash

awk '{ 
    if (NR > 1) { 
        sum = 0; 
        for (i = 2; i <= NF; i++) sum += $i; 
        avg = sum / (NF - 1); 
        print $1, avg; 
    } 
}' oceny.txt
