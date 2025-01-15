#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>

int main() {
    pid_t pid;
    int i;

    printf("Process 0, PID: %d\n", getpid());

    for (i = 1; i <= 10; i++) {
        pid = fork();

        if (pid == 0) {
            // Proces potomny
            printf("Process %d, PID: %d, PPID: %d\n", i, getpid(), getppid());
        } else if (pid > 0) {
            // Proces macierzysty
            wait(NULL); // Czekaj na zakończenie potomka
            exit(0); // Proces macierzysty kończy działanie po zakończeniu dziecka
        } else {
            // Błąd forka
            perror("fork");
            exit(1);
        }
    }

    return 0;
}