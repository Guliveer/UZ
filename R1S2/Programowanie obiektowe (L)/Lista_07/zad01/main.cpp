/* Napisać klasę reprezentującą człowieka, zawierającą jego imię. Klasę uzupełnić o
 * - konstruktor
 * - destruktor z domyślną implementacją
 * - funkcję wypiszDane służącą do wypisania do strumienia informacji o człowieku (jego imię). Argumentem metody ma być referencja do ostream, zaś wartością zwracaną — void.
 * - funkcję wypiszKlasę służącą do wypisania do strumienia informacji o klasie reprezentującej człowieka (nazwę klasy). Argumentem metody ma być referencja do ostream, zaś wartością zwracaną — void.
 * - operator umożliwiający wpisanie do strumienia (np. cout) danych o człowieku. W operatorze wykorzystać funkcję wypiszKlasę oraz wypiszDane.
 * - funkcję statyczną służącą do dynamicznego tworzenia nowych obiektów klasy Czlowiek. Funkcja ma pytać użytkownika o dane (wprowadzane z klawiatury), a następnie ma dynamicznie utworzyć nowy obiekt. Wartością zwracaną ma być wskaźnik na ten nowoutworzony obiekt.
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


int main() {
    Czlowiek *czlowiek = Czlowiek::create();
    cout << *czlowiek;

    return EXIT_SUCCESS;
}
