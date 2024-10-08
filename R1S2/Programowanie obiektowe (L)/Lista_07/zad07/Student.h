//
// Created by Oliwer Pawelski on 11/06/2024.
//

#ifndef ZAD07_STUDENT_H
#define ZAD07_STUDENT_H

#include "Czlowiek.h"
#include <iostream>
#include <string>

class Student : public Czlowiek {
private:
    std::string imie;
    int numerIndeksu;

public:
    ~Student() override = default; // destruktor
    explicit Student(std::string name); // konstruktor
    Student(std::string name, int numerIndeksu); // konstruktor

    void wypiszDane(std::ostream &os) override;

    static void wypiszKlase(std::ostream &os);

    friend std::ostream &operator<<(std::ostream &os, Student &student);

    static Student *create();
};


#endif //ZAD07_STUDENT_H
