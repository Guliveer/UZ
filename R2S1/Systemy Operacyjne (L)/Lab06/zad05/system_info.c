#include <stdio.h>
#include <stdlib.h>
#include <sys/utsname.h>
#include <unistd.h>

int main() {
    // Pobieranie nazwy hosta
    char hostname[1024];
    if (gethostname(hostname, sizeof(hostname)) == -1) {
        perror("Nie można pobrać nazwy hosta");
        return 1;
    }

    // Pobieranie informacji o systemie operacyjnym
    struct utsname system_info;
    if (uname(&system_info) == -1) {
        perror("Nie można pobrać informacji o systemie");
        return 1;
    }

    // Wyświetlanie informacji
    printf("Informacje o komputerze:\n");
    printf("Nazwa hosta: %s\n", hostname);
    printf("System operacyjny: %s\n", system_info.sysname);
    printf("Nazwa węzła: %s\n", system_info.nodename);
    printf("Wersja systemu: %s\n", system_info.version);
    printf("Wydanie systemu: %s\n", system_info.release);
    printf("Architektura: %s\n", system_info.machine);

    return 0;
}
