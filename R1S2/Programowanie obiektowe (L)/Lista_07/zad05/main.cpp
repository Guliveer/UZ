/*
 * Zmienić klasę ProstaListaOsob tak, aby możliwe było dodanie (w dowolnej kolejności) do listy osób jednego
 * studenta. Argumentem konstruktora pozostaje liczba osób (liczba studentów jest stała i wynosi 1).
 *
 * UWAGA! Zwrócić uwagę, że przy wprowadzaniu danych Student może pojawić się na LiścieOsób w dowolnym
 * miejscu. Przy wyświetlaniu listy należy uwzględnić miejsce Studenta. Oznacza to, że Student może pojawić
 * się w dowolnym miejscu, NIE tylko na początku bądź końcu listy. Zmienić program tak, by demonstrował
 * nowe możliwości klasy ProstaListaOsob.
 */

#include <iostream>
#include <utility>

using namespace std;

class Czlowiek {
private:
    string imie;
public:
    explicit Czlowiek(string name) {
        imie = std::move(name);
    };

    void wypiszDane(ostream &os) {
        os << imie << endl;
    };

    static void wypiszKlase(ostream &os) {
        os << "Czlowiek" << endl;
    };

    friend ostream &operator<<(ostream &os, Czlowiek &czlowiek) {
        Czlowiek::wypiszKlase(os);
        czlowiek.wypiszDane(os);
        return os;
    };

    static Czlowiek *create() {
        string name;
        cout << "Podaj imie: ";
        cin >> name;
        return new Czlowiek(name);
    };

    ~Czlowiek() = default;
};

class ProstaListaOsob {
private:
    Czlowiek **osoby;
    int liczbaOsob;
public:
    explicit ProstaListaOsob(int liczba) {
        liczbaOsob = liczba;
        osoby = new Czlowiek *[liczbaOsob];
    };

    void dodaj(Czlowiek *czlowiek, int index) {
        if (index < 0 || index >= liczbaOsob) {
            cout << "Niepoprawny index" << endl;
            return;
        }
        if (osoby[index] != nullptr) {
            cout << "Miejsce jest juz zajete" << endl;
            return;
        }
        osoby[index] = czlowiek;
    };

    void wypiszDane(ostream &os) {
        for (int i = 0; i < liczbaOsob; i++) {
            if (osoby[i] != nullptr) {
                os << *osoby[i];
            }
        }
    };

    static void wypiszKlase(ostream &os) {
        os << "ProstaListaOsob" << endl;
    };

    friend ostream &operator<<(ostream &os, ProstaListaOsob &prostaListaOsob) {
        ProstaListaOsob::wypiszKlase(os);
        prostaListaOsob.wypiszDane(os);
        return os;
    };

    ~ProstaListaOsob() {
        for (int i = 0; i < liczbaOsob; i++) {
            delete osoby[i];
        }
        delete[] osoby;
    };
};

int main() {
    Czlowiek *person1 = Czlowiek::create();
    cout << *person1;
    cout << "\n";

    Czlowiek *person2 = Czlowiek::create();
    cout << *person2;
    cout << "\n";

    Czlowiek *person3 = Czlowiek::create();
    cout << *person3;
    cout << "\n";

    ProstaListaOsob lista(3);
    lista.dodaj(person1, 0);
    lista.dodaj(person2, 2);
    lista.dodaj(person3, 1);

    cout << "\n";
    cout << lista;

    return EXIT_SUCCESS;
}

