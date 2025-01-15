#include <stdio.h>
#include <stdlib.h>
#include <string.h>  // Dodane include dla strcmp
#include <unistd.h>

void delete_file(const char *path) {
    if (unlink(path) == -1) {
        perror("unlink");
    }
}

void delete_directory(const char *path) {
    if (rmdir(path) == -1) {
        perror("rmdir");
    }
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Użycie: %s <plik/katalog> <R (jeśli katalog z podkatalogami)>\n", argv[0]);
        return 1;
    }

    if (argc == 3 && strcmp(argv[2], "R") == 0) {
        // Kod rekurencyjny do usuwania katalogu wraz z podkatalogami (nie implementowane w tym przypadku)
    } else {
        delete_file(argv[1]);
        delete_directory(argv[1]);
    }

    return 0;
}
