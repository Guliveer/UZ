/* Uzupełnić klasę ProstaListaOsob o możliwość przechowywania dowolnej, większej od zera liczby osób.
 * Liczba ta podawana jest jako argument konstruktora. Zmienić program demonstrujący działanie tej klasy tak,
 * aby uwzględniał jej nowe możliwości
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

    void dodaj(Czlowiek *czlowiek) {
        for (int i = 0; i < liczbaOsob; i++) {
            if (osoby[i] == nullptr) {
                osoby[i] = czlowiek;
                return;
            }
        }
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
    lista.dodaj(person1);
    lista.dodaj(person2);
    lista.dodaj(person3);

    cout << "\n";
    cout << lista;

    return EXIT_SUCCESS;
}
