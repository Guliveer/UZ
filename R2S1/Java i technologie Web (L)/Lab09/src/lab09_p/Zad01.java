// Oliwer Pawelski, 24INF-SP/A
//
// Opracuj i zrealizuj kod programu, którego zadaniem będzie zdefiniowanie klasy
// 'Student' o polach: Imie, Nazwisko, Indeks oraz metod Ustaw_Imie, Ustaw_nazwisko, Ustaw_indeks.

package lab09_p;

// I know that I should put this class in a differently named file, but it's there, because it's a part of this task.
class Student {
    private String name;
    private String surname;
    private int index;

    Student() { // Default constructor
        this.name = "";
        this.surname = "";
        this.index = 0;
    }

    Student(String name, String surname, int index) { // Parameterized constructor
        this.name = name;
        this.surname = surname;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}