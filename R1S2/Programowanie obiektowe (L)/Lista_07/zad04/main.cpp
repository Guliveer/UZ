/* Napisać klasę reprezentującą studenta, zawierającą jego imię i numer indeksu. Klasę uzupełnić o:
 * - konstruktor
 * - destruktor z domyślną implementacją
 * - funkcję wypiszDane służącą do wypisania do strumienia informacji o studencie (jego imię i numer indeksu). Argumentem metody ma być referencja do ostream, zaś wartością zwracaną — void.
 * - funkcję wypiszKlasę służącą do wypisania do strumienia informacji o klasie reprezentującej studenta (nazwę klasy). Argumentem metody ma być referencja do ostream, zaś wartością zwracaną — void.
 * - operator umożliwiający wpisanie do strumienia (np. cout) danych o studencie. W operatorze wykorzystać funkcję wypiszKlasę oraz wypiszDane.
 * - funkcję statyczną służącą do dynamicznego tworzenia nowych obiektów klasy Student. Funkcja ma pytać użytkownika o dane (wprowadzane z klawiatury), a następnie ma dynamicznie utworzyć nowy obiekt. Wartością zwracaną ma być wskaźnik na ten nowoutworzony obiekt.
 */

#include <iostream>
#include <utility>

using namespace std;

class Student {
private:
    string imie;
    int numerIndeksu;

public:
    Student(string imie, int numerIndeksu) : imie(std::move(imie)), numerIndeksu(numerIndeksu) {} // konstruktor
    ~Student() = default; // destruktor
    void wypiszDane(ostream &os) const { // funkcja wypiszDane
        os << "Imie: " << imie << ", numer indeksu: " << numerIndeksu << endl;
    }

    static void wypiszKlase(ostream &os) { // funkcja wypiszKlasę
        // funkcja jest statyczna, bo nie ma dostępu do pól obiektu
        os << "Student" << endl;
    }

    friend ostream &operator<<(ostream &os, const Student &student) { // operator <<
        Student::wypiszKlase(os); // Student:: bo funkcja statyczna
        student.wypiszDane(os);
        return os;
    }

    static Student *utworzStudenta() { // funkcja statyczna
        string imie;
        int numerIndeksu;
        cout << "Podaj imie: ";
        cin >> imie;
        cout << "Podaj numer indeksu: ";
        cin >> numerIndeksu;
        return new Student(imie, numerIndeksu);
    }
};

int main() {
    Student *student = Student::utworzStudenta();
    cout << *student;
    delete student;

    return EXIT_SUCCESS;
}
