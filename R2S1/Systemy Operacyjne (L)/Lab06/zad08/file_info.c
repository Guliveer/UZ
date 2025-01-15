#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <pwd.h>
#include <grp.h>
#include <time.h>

void print_file_type(mode_t mode) {
    if (S_ISREG(mode)) printf("Typ: zwykły plik\n");
    else if (S_ISDIR(mode)) printf("Typ: katalog\n");
    else if (S_ISCHR(mode)) printf("Typ: urządzenie znakowe\n");
    else if (S_ISBLK(mode)) printf("Typ: urządzenie blokowe\n");
    else if (S_ISFIFO(mode)) printf("Typ: potok (FIFO)\n");
    else if (S_ISLNK(mode)) printf("Typ: dowiązanie symboliczne\n");
    else if (S_ISSOCK(mode)) printf("Typ: gniazdo\n");
    else printf("Typ: nieznany\n");
}

void print_permissions(mode_t mode) {
    printf("Prawa dostępu: ");
    printf( (mode & S_IRUSR) ? "r" : "-");
    printf( (mode & S_IWUSR) ? "w" : "-");
    printf( (mode & S_IXUSR) ? "x" : "-");
    printf( (mode & S_IRGRP) ? "r" : "-");
    printf( (mode & S_IWGRP) ? "w" : "-");
    printf( (mode & S_IXGRP) ? "x" : "-");
    printf( (mode & S_IROTH) ? "r" : "-");
    printf( (mode & S_IWOTH) ? "w" : "-");
    printf( (mode & S_IXOTH) ? "x" : "-");
    printf("\n");
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Użycie: %s <ścieżka do pliku>\n", argv[0]);
        return 1;
    }

    struct stat file_stat;
    if (stat(argv[1], &file_stat) == -1) {
        perror("stat");
        return 1;
    }

    // Typ pliku
    print_file_type(file_stat.st_mode);

    // Właściciel i grupa
    struct passwd *pw = getpwuid(file_stat.st_uid);
    struct group *gr = getgrgid(file_stat.st_gid);
    printf("Właściciel: %s\n", pw ? pw->pw_name : "nieznany");
    printf("Grupa: %s\n", gr ? gr->gr_name : "nieznana");

    // Prawa dostępu
    print_permissions(file_stat.st_mode);

    // Czasy dostępu
    printf("Czas ostatniego dostępu: %s", ctime(&file_stat.st_atime));
    printf("Czas ostatniej modyfikacji: %s", ctime(&file_stat.st_mtime));
    printf("Czas ostatniej zmiany i-węzła: %s", ctime(&file_stat.st_ctime));

    // Rozmiar pliku
    printf("Rozmiar: %lld bajtów\n", (long long)file_stat.st_size);

    return 0;
}
