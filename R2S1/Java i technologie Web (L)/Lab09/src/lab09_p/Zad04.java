// Oliwer Pawelski, 24INF-SP/A
//
// W nowej klasie testowej Test utwórz obiekt klasy StudentInstytut oraz zdefiniuj kod,
// który korzystając z podanych metod pozwoli użytkownikowi na zdefiniowanie
// wartości każdego z pól.

package lab09_p;

class Test {
    public static void main(String[] args) {
        StudentInstytut studentInstytut = new StudentInstytut();
        studentInstytut.setName("Oliwer");
        studentInstytut.setSurname("Pawelski");
        studentInstytut.setIndex(123456);
        studentInstytut.setFaculty("Wydział Informatyki, Elektrotechniki i Automatyki");
        studentInstytut.setInstitute("Instytut Sterowania i Systemów Informatycznych");

        System.out.println(studentInstytut);
    }
}
