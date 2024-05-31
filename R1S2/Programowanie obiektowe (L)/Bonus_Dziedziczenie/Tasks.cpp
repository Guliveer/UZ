#include <iostream>
using namespace std;

void newline() {
    cout << "\n";
}

class A {
    public:
    A() {cout << "A";}
    virtual void f() {cout << "fA";} // 'Opóźnienie' wiązania; polimorfizm
};

class B:public A {
    public:
    B() {cout << "B";}
    virtual void f() {cout << "fB";} // "virtual" wstawiamy wszędzie (tak dla pewności i bezpieczeństwa) jeśli jest już wcześniej wstawiony w jakiejś dziedziczonej klasie
};

// Późne wiązanie (Late binding)
void g(A obj) {
    obj.f();
};

// Wczesne wiązanie (Early binding)
void h(A *obj) {
    obj->f();
};


// W kl zwierze funkcja wypisuje "zwierze", a w kl pies wypisuje "Cześć {}", gdzie {} jest definiowane w konstruktorze
class Zwierze {
    public:
    bool type = 0;
    Zwierze() {};
    void wypisz() {
        cout << "Zwierze\n";
    }
};

class Pies:public Zwierze {
    private:
    string name;
    
    public:
    Pies(string tmp) {
        name = tmp;
        type = 1;
    }
    void wypisz(){
        cout << "Cześć, " << name << "\n";
    }
};

// Napisać globalną funkcję wypisz(), której zadaniem jest wypisanie obiektu dowolnej klasy (w zależności od argumentu)
void wypisz(Zwierze* tmp){
    if (tmp->type){
        ((Pies*)tmp)->wypisz();
    } else {
        tmp->wypisz();
    };
}

int main () {
    
    Zwierze objZ;
    Pies objP("Czarek");
    
    objZ.wypisz(); // Zwierze
    objP.wypisz(); // Cześć, {}
    
    wypisz(&objP);

  return 0;
}
