// THIS SHIT IS HELLFIRE

#include <iostream>
#include <cstdlib> // dla rand()

using namespace std;


class Punkt {
public:
    double x;
    double y;
    double z;

    Punkt(double x = 0, double y = 0, double z = 0) : x(x), y(y), z(z) {}
};

class Wektor {
public:
    Punkt *points;
    int size;

    Wektor() : size(0), points(nullptr) {}

    void addPoint(const Punkt &punkt) {
        auto *newTab = new Punkt[size + 1];

        for (int i = 0; i < size; i++) {
            newTab[i] = points[i];
        }

        newTab[size] = punkt;

        delete[] points;
        points = newTab;
        size++;
    }

    Wektor operator+(const Wektor &inny) const {
        Wektor result;
        for (int i = 0; i < size; i++) {
            result.addPoint(Punkt(points[i].x + inny.points[i].x, points[i].y + inny.points[i].y,
                                  points[i].z + inny.points[i].z));
        }
        return result;
    }

    Wektor operator-(const Wektor &inny) const {
        Wektor result;
        for (int i = 0; i < size; i++) {
            result.addPoint(Punkt(points[i].x - inny.points[i].x, points[i].y - inny.points[i].y,
                                  points[i].z - inny.points[i].z));
        }
        return result;
    }

    double dotProduct(const Wektor &inny) const {
        double wynik = 0;
        for (int i = 0; i < size; i++) {
            wynik += points[i].x * inny.points[i].x + points[i].y * inny.points[i].y + points[i].z * inny.points[i].z;
        }
        return wynik;
    }

    Wektor vectorProduct(const Wektor &inny) const {
        Wektor wynik;
        for (int i = 0; i < size; i++) {
            double x = points[i].y * inny.points[i].z - points[i].z * inny.points[i].y;
            double y = points[i].z * inny.points[i].x - points[i].x * inny.points[i].z;
            double z = points[i].x * inny.points[i].y - points[i].y * inny.points[i].x;
            wynik.addPoint(Punkt(x, y, z));
        }
        return wynik;
    }

    void input() {
        cout << "Podaj liczbe punktow: ";
        cin >> size;
        delete[] points;
        points = new Punkt[size];
        for (int i = 0; i < size; i++) {
            cout << "Podaj wspolrzedne punktu " << i + 1 << ": ";
            cin >> points[i].x >> points[i].y >> points[i].z;
        }
    }

    void generate() {
        cout << "Podaj liczbe punktow: ";
        cin >> size;
        delete[] points;
        points = new Punkt[size];
        srand(time(nullptr));
        for (int i = 0; i < size; i++) {
            points[i].x = static_cast<double>(rand()) / RAND_MAX;
            points[i].y = static_cast<double>(rand()) / RAND_MAX;
            points[i].z = static_cast<double>(rand()) / RAND_MAX;
        }
    }

    void disp() const {
        for (int i = 0; i < size; i++) {
            cout << "Punkt " << i + 1 << ": (" << points[i].x << ", " << points[i].y << ", " << points[i].z << ")\n";
        }
    }

    ~Wektor() {
        delete[] points;
    }
};

int main() {
    Wektor w1, w2;
    w1.input();
    w2.generate();
    cout << "\n";

    cout << "Wektor 1:\n";
    w1.disp();
    cout << "Wektor 2:\n";
    w2.disp();
    cout << "\n";

    cout << "Wektor 1 + Wektor 2:\n";
    (w1 + w2).disp();
    cout << "\n";

    cout << "Wektor 1 - Wektor 2:\n";
    (w1 - w2).disp();
    cout << "\n";

    cout << "Iloczyn skalarny Wektor 1 i Wektor 2: " << w1.dotProduct(w2) << endl;
    cout << "\n";

    cout << "Iloczyn wektorowy Wektor 1 i Wektor 2:\n";
    (w1.vectorProduct(w2)).disp();

    return EXIT_SUCCESS;
}
