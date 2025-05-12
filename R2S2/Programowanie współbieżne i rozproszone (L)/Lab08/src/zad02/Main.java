// Korzystając z bariery (CyclicBarrier) napisz program obliczający średnią ocenę semestralną studenta.
// Program składa się z czterech wątków, każdy wątek to inny prowadzący zajęcia. W pewnym
// momencie pod koniec semestru prowadzący wystawia ocenę końcową z przedmiotu, przy czym
// każdy prowadzący może to zrobić w innym czasie. W momencie kiedy wszyscy prowadzący
// wystawią już swoje oceny należy uruchomić kolejny wątek Srednia, który policzy średnią ocenę ze
// wszystkich ocen uzyskanych u prowadzących.

package zad02;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        final int numberOfInstructors = 4;
        double[] grades = new double[numberOfInstructors];
        CyclicBarrier barrier = new CyclicBarrier(numberOfInstructors, () -> {
            // This task runs after all instructors have submitted their grades
            double sum = 0;
            for (double grade : grades) {
                sum += grade;
            }
            double average = sum / numberOfInstructors;
            System.out.println("Average semester grade: " + average);
        });

        for (int i = 0; i < numberOfInstructors; i++) {
            final int instructorIndex = i;
            new Thread(() -> {
                try {
                    // Simulate grade assignment
                    // Random grade between 2 and 5
                    int grade = (int) (Math.random() * 3 + 2);
                    grades[instructorIndex] = grade;
                    System.out.println("Instructor " + (instructorIndex + 1) + " assigned grade: " + grade);
                    barrier.await(); // Wait for other instructors
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
