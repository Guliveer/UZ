//
// Created by Oliwer Pawelski on 11/06/2024.
//

#include "Student.h"
#include <utility>

Student::Student(std::string name) : Czlowiek(std::move(name)) {
    imie = std::move(name);
    std::cout << "Podaj numer indeksu: ";
    int tmp;
    std::cin >> tmp;
    numerIndeksu = tmp;
};

Student::Student(std::string name, int index) : Czlowiek(std::move(name)) {
    imie = std::move(name);
    numerIndeksu = index;
};

void Student::wypiszDane(std::ostream &os) {
    os << "Imie: " << imie << ", numer indeksu: " << numerIndeksu << std::endl;
};

void Student::wypiszKlase(std::ostream &os) {
    os << "Student" << std::endl;
};

std::ostream &operator<<(std::ostream &os, Student &student) {
    Student::wypiszKlase(os);
    student.wypiszDane(os);
    return os;
};

Student *Student::create() {
    std::string imie;
    int numerIndeksu;

    std::cout << "Podaj imie: ";
    std::cin >> imie;
    std::cout << "Podaj numer indeksu: ";
    std::cin >> numerIndeksu;
    return new Student(imie, numerIndeksu);
}
