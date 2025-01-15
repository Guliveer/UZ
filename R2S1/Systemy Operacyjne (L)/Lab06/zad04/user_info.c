#include <stdio.h>
#include <stdlib.h>
#include <pwd.h>
#include <grp.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>

void print_user_info(const char *username) {
    struct passwd *pw = getpwnam(username);
    if (!pw) {
        perror("Nie znaleziono użytkownika");
        return;
    }

    printf("Informacje o użytkowniku: %s\n", username);
    printf("UID: %d\n", pw->pw_uid);
    printf("GID: %d\n", pw->pw_gid);
    printf("Katalog domowy: %s\n", pw->pw_dir);
    printf("Powłoka: %s\n", pw->pw_shell);

    // Pobieranie grup użytkownika
    printf("Grupy: ");
    int ngroups = 0;
    getgrouplist(username, pw->pw_gid, NULL, &ngroups); // Pobranie liczby grup
    gid_t groups_gid[ngroups];
    int groups_int[ngroups];

    if (getgrouplist(username, pw->pw_gid, (int *)groups_int, &ngroups) == -1) {
        perror("Błąd podczas pobierania grup");
        return;
    }

    // Konwersja z int do gid_t
    for (int i = 0; i < ngroups; i++) {
        groups_gid[i] = (gid_t)groups_int[i];
    }

    for (int i = 0; i < ngroups; i++) {
        struct group *gr = getgrgid(groups_gid[i]);
        if (gr) {
            printf("%s%s", gr->gr_name, (i < ngroups - 1) ? ", " : "");
        }
    }
    printf("\n");
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Użycie: %s <nazwa_uzytkownika>\n", argv[0]);
        return 1;
    }

    print_user_info(argv[1]);
    return 0;
}
