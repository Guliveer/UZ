/*
 * Rysunek pomocniczy:
 *
 *  p [  o--]---->[  1  /  o--]---->[  5  /  o  ]
 *       /\                                  |
 *       |                                   |
 *       --------------------[--o  /  7  ]<---
 */


#include <iostream>

using namespace std;

struct Elem3;
struct Elem1 {
    int val;
    Elem3 *ptr;
};

struct Elem2 {
    int val;
    Elem1 **ptr;
};

struct Elem3 {
    int val;
    Elem2 *ptr;
};

int
main() {
    Elem1 *p; // wskaźnik na strukturę Elem1

    p = new Elem1; // alokacja pamięci dla struktury Elem1
    (*p).val = 1; // przypisanie wartości do pola val struktury Elem1
    (*p).ptr = new Elem3;
    (*((*p).ptr)).val = 5;
    (*((*p).ptr)).ptr = new Elem2;
    (*((*((*p).ptr)).ptr)).val = 7;
    (*((*((*p).ptr)).ptr)).ptr = &p; // wskaźnik na wskaźnik; powrót do p


    /* Alternatywa:
    *
    * p->val = 1;
    * p->ptr = new Elem3;
    * p->ptr->val = 5;
    * p->ptr->ptr = new Elem2;
    * p->ptr->ptr->val = 7;
    * p->ptr->ptr->ptr = &p;
    */



    cout
            << "p->val = "
            << p->val
            << endl;
    cout
            << "p->ptr->val = "
            << p->ptr->val
            << endl;
    cout
            << "p->ptr->ptr->val = "
            << p->ptr->ptr->val
            << endl;
    cout
            << "p->ptr->ptr->ptr->val = "
            << (*p->ptr->ptr->ptr)->val
            << endl;

    delete (*((*p).ptr)).ptr; // zwolnienie pamięci
    delete (*p).ptr;
    delete p;
    p = nullptr; // wartość 'nullptr', bo inaczej p wskazuje śmietnik

    return EXIT_SUCCESS;
}
