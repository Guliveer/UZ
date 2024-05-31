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
}

// Wczesne wiązanie (Early binding)
void h(A *obj) {
    obj->f();
}

int main () {
    A objA; newline();
    B objB; newline();
    
    int x; cin >> x;
    A* wsk;
    if (x % 2 == 0){
        wsk = &objA;
    } else {
        wsk = &objB;
    }
    
    wsk->f(); newline();
    
    g(objB); newline();
    h(&objB); newline();

  return 0;
}
