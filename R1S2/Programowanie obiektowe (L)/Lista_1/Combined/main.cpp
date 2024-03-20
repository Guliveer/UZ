#include <iostream>
#include <thread>
#include <cstdlib>

using namespace std;

void sleep(const int s) {
    std::this_thread::sleep_for(std::chrono::seconds(s)); // timeout w sekundach
}

void task1() {
    cout << "Witaj swiecie" << endl;
}

void task2() {
    for (int i = 0; i < 13; i++) {
        cout << i + 1 << ". Witaj swiecie" << endl;
    }
}

int task3(const int arg) {
    return arg + 1;
}

int task4(const int arg) {
    return arg + 1;
}

void task5() {
    for (int i = 0; i < 18; i++) {
        cout << "Argument = " << i << ", zwiekszacz(" << i << ") = " << task3(i) << endl;
    }
}

double task6(const int x1, const int y1, const int z1, const int x2, const int y2, const int z2) {
    return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2) + pow(z1 - z2, 2));
}

int task7(const int a, const int b, const int c) {
    if (a > b && a > c) return a;
    if (b > a && b > c) return b;
    return c;
}

void task8() {
    cout << "(Litera) => (Kod ASCII)" << endl;
    for (unsigned char i = 'a'; i <= 'z'; i++) {
        const int a = i;
        cout << i << " => " << a << endl;
    }
}

void task9() {
    for (int i = 1; i <= 9; i++) {
        for (int j = 1; j <= i; j++) {
            cout << i;
        }
        cout << endl;
    }
}

void task10() {
    srand(time(nullptr));
    const int size = rand() % 16 + 10;
    float numbers[size];
    for (int i = 0; i < size; i++) {
        numbers[i] = rand() % 100;
    }

    float min = numbers[0];
    float max = numbers[0];

    for (const float number: numbers) {
        if (number < min) {
            min = number;
        }
        if (number > max) {
            max = number;
        }
    }

    cout << "Liczby: ";
    for (const float number: numbers) {
        cout << number << " ";
    }
    cout << endl;
    cout << "Minimum: " << min << endl;
    cout << "Maximum: " << max << endl;
}

void menu() {
    cout << "Podaj numer zadania (1-19): ";
    // Pobranie wyboru
    int option = 0, arg, arg2, arg3, arg4, arg5, arg6;
    cin >> option;
    cout << endl;

    switch (option) {
        case 1:
            cout << "Zadanie 1: program wyświetlający na ekranie tekst \'witaj swiecie\'" << endl;
            task1();
            break;
        case 2:
            cout << "Zadanie 2: program wyświetlający na ekranie 13 razy tekst \'witaj swiecie\'. Wykorzystać pętlę for" << endl;
            task2();
            break;
        case 3:
            cout <<
                    "Zadanie 3: funkcja  przyjmującą jako argument jedną liczbę całkowitą i zwracającą liczbę całkowitą o jeden większą od podanej. W funkcji NIE wolno umieszczać żadnych operacji wejścia - wyjścia ze szczególnym uwzględnieniem operacji wczytania wartości zmiennej z klawiatury." << endl;
            arg = 2;
            cout << "x = " << arg << " -> x+1 = " << task3(2) << endl;
            break;
        case 4:
            cout << "Zadanie 4: Dopisać do funkcji z podpunktu 3 program główny w którym należy zapytać użytkownika o liczbę a następnie wyświetlić wynik działania funkcji" << endl;
            cout << "Podaj x: ";
            cin >> arg;
            cout << "x = " << arg << " -> x+1 = " << task4(arg) << endl;
            break;
        case 5:
            cout << "Zadanie 5: Wykorzystując funkcję z podpunktu 3 i pętlę for napisać program który wydrukuje na ekrenie tekst: Argument: 0, zwiekszacz(0) = 1 itd. dla argumentów o wartości od 0 do 17" << endl;
            task5();
            break;
        case 6:
            cout << "Zadanie 6: Napisać funkcję wyznaczającą odległość d miedzy dwoma punktami o współrzędnych (x1, y1, z1) i (x2, y2, z2) w przestrzeni trójwymiarowej wg wzoru: d = √((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)" << endl;
            cout << "[ Podaj wartości ]" << endl;
            cout << "x1: ";
            cin >> arg;
            cout << "y1: ";
            cin >> arg2;
            cout << "z1: ";
            cin >> arg3;
            cout << endl;
            cout << "x2: ";
            cin >> arg4;
            cout << "y2: ";
            cin >> arg5;
            cout << "z2: ";
            cin >> arg6;
            cout << "\nd = √((" << arg << "-" << arg4 << ")^2 + (" << arg2 << "-" << arg5 << ")^2 + (" << arg3 << "-" << arg6 << ")^2)\nd = " << task6(arg, arg2, arg3, arg4, arg5, arg6) << endl;
            break;
        case 7:
            cout << "Zadanie 7: Napisać funkcję wyznaczającą maksymalną wartość z trzech liczb a, b i c podanych jako argumenty. Napisać program główny ilustrujący użycie tej funkcji." << endl;
            cout << "[ Podaj wartości ]" << endl;
            cout << "a = ";
            cin >> arg;
            cout << "b = ";
            cin >> arg2;
            cout << "c = ";
            cin >> arg3;
            cout << "\nmax(a, b, c) = " << task7(arg, arg2, arg3) << endl;
            break;
        case 8:
            cout << "Zadanie 8: Napisać program wyświetlający znaki alfabetu wraz z odpowiadającymi im kodami ASCII. Zastosować pętlę ze zmienną sterujacą typu char." << endl;
            task8();
            break;
        case 9:
            cout << "Zadanie 9: Napisać bezargumentową funkcję, która nic nie zwraca, która wypisze na ekranie trójkąt z cyfr 1,2,...9 w kolejnych wierszach. W wierszu 1 ma zostać wydrukowany jeden znak 1, w drugim wierszu — 2 razy cyfra 2, w trzecim — trzy razy cyfra 3, w dziewiątym — dziewięć razy cyfra 9. W programie głównym wywołać tę funkcję." << endl;
            task9();
            break;
        case 10:
            cout << "Zadanie 10: Napisać program wyznaczający minimum i maksimum w tablicy jednowymiarowej liczb typu float." << endl;
            task10();
            break;
        // case 11:
        //     task11();
        //     break;
        // case 12:
        //     task12();
        //     break;
        // case 13:
        //     task13();
        //     break;
        // case 14:
        //     task14();
        //     break;
        // case 15:
        //     task15();
        //     break;
        // case 16:
        //     task16();
        //     break;
        // case 17:
        //     task17();
        //     break;
        // case 18:
        //     task18();
        //     break;
        // case 19:
        //     task19();
        //     break;
        default:
            cout << "Niepoprawny wybor!" << endl;
            break;
    }

    cout << "\n-------\n\n";
}

int main() {
    while (true) {
        menu();
    }
}
