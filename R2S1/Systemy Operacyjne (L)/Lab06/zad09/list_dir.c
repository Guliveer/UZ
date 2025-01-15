#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <string.h>
#include <sys/stat.h>
#include <unistd.h>

void list_directory(const char *path, int recursive) {
    DIR *dir = opendir(path);
    struct dirent *entry;
    struct stat statbuf;

    if (!dir) {
        perror("opendir");
        return;
    }

    while ((entry = readdir(dir)) != NULL) {
        // Ignorujemy "." i ".."
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
            continue;

        // Pełna ścieżka do pliku/katalogu
        char full_path[1024];
        snprintf(full_path, sizeof(full_path), "%s/%s", path, entry->d_name);

        // Sprawdzamy typ pliku (katalog, zwykły plik)
        if (stat(full_path, &statbuf) == -1) {
            perror("stat");
            continue;
        }

        printf("%s\n", full_path);

        if (recursive && S_ISDIR(statbuf.st_mode)) {
            list_directory(full_path, recursive);  // Rekurencyjnie dla podkatalogów
        }
    }

    closedir(dir);
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        fprintf(stderr, "Użycie: %s <ścieżka do katalogu> [R]\n", argv[0]);
        return 1;
    }

    int recursive = (argc > 2 && strcmp(argv[2], "R") == 0);
    list_directory(argv[1], recursive);

    return 0;
}
