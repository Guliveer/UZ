// Oliwer Pawelski, 24INF-SP/A
//
// Kolejno dodaj klasę StudentInstytut, która będzie dziedziczyć z klasy StudentUZ oraz
// zawierać dodatkowe pole Instytut wraz z metodą Ustaw_instytut oraz metodę
// getInstytut, która zwróci nazwę instytutu.

package lab09_p;

class StudentInstytut extends StudentUZ {
    private String institute;

    StudentInstytut() {
        super();
        this.institute = "";
    }

    StudentInstytut(String name, String surname, int index, String faculty, String institute) {
        super(name, surname, index, faculty);
        this.institute = institute;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Override
    public String toString() {
        return "StudentInstytut {\n" +
                "\tname='" + getName() + "',\n" +
                "\tsurname='" + getSurname() + "',\n" +
                "\tindex=" + getIndex() + ",\n" +
                "\tfaculty='" + getFaculty() + "',\n" +
                "\tinstitute='" + getInstitute() + '\'' +
                "\n}";
    }
}
