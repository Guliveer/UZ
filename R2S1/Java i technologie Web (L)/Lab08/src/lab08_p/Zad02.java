// Oliwer Pawelski, 24INF-SP/A

// Stwórz klasę opisującą studenta (imię, nazwisko, numer indeksu), a następnie stwórz
// jej listę obiektów. Podaj ile elementów zawiera lista, oraz co znajduje się w 3
// elemencie tej listy.

package lab08_p;

class Student {
    private final String name;
    private final String surname;
    private final int index;

    public Student(String name, String surname, int index) {
        this.name = name;
        this.surname = surname;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getIndex() {
        return index;
    }
}

public class Zad02 {
    public static void main(String[] args) {
        Student[] students = new Student[]{
                new Student("Oliwer", "Pawelski", 123456),
                new Student("Jan", "Kowalski", 543210),
                new Student("Adam", "Nowak", 213769)
        };

        System.out.println("Liczba elementów w tablicy: " + students.length);
        System.out.println("3 element tablicy: " + students[2].getName() + " " + students[2].getSurname() + " " + students[2].getIndex());
    }
}
