#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

void move_file(const char *src, const char *dest) {
    if (rename(src, dest) == -1) {
        perror("rename");
    }
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Użycie: %s <źródło> <cel>\n", argv[0]);
        return 1;
    }

    move_file(argv[1], argv[2]);
    return 0;
}
