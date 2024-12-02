// Oliwer Pawelski, 24INF-SP/A
//
// Dodaj nową klasę StudentUZ, która będzie dziedziczyć z klasy Student oraz zawierać
// dodatkowe pole Wydzial i metodę Ustaw_wydzial.

package lab09_p;

class StudentUZ extends Student {
    private String faculty;

    StudentUZ() { // Default constructor
        super();
        this.faculty = "";
    }

    StudentUZ(String name, String surname, int index, String faculty) { // Parameterized constructor
        super(name, surname, index);
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
