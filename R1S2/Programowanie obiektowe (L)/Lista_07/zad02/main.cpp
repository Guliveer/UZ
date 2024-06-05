/* Napisać klasę ProstaListaOsob, reprezentującą dwie osoby. Klasa powinna posiadać:
 * - operator umożliwiający wpisanie do strumienia (np. cout) po kolei informacji o wszystkich osobach na liście, wraz z ich numerem na liście (numery zaczynają się od 1).
 * - funkcję operatorową dodaj umożliwiającą dodanie nowej osoby do listy. Jeśli lista zawiera już dwie osoby, zwrócić komunikat o błędzie i nie dopisywać kolejnej osoby.
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
    Czlowiek *osoby[2]{};
    int iloscOsob = 0;
public:
    ProstaListaOsob() {
        for (auto &i: osoby) {
            i = nullptr;
        }
    };

    void dodaj(Czlowiek *czlowiek) {
        if (iloscOsob < 2) {
            osoby[iloscOsob] = czlowiek;
            iloscOsob++;
            cout << "Dodano osobe do listy" << endl;
        } else {
            cout << "Lista jest pelna" << endl;
        }
    };

    friend ostream &operator<<(ostream &os, ProstaListaOsob &lista) {
        for (int i = 0; i < 2; i++) {
            os << i + 1 << ". ";
            if (lista.osoby[i] != nullptr) {
                os << *lista.osoby[i];
            } else {
                os << "Brak osoby" << endl;
            }
        }
        return os;
    };

    ~ProstaListaOsob() {
        for (auto &i: osoby) {
            delete i;
        }
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

    ProstaListaOsob lista;
    lista.dodaj(person1);
    lista.dodaj(person2);
    lista.dodaj(person3);

    cout << "\n";
    cout << lista;

    return EXIT_SUCCESS;
}
