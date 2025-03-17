package lab02_p.Zad01;

public class Space3D extends Space2D {
    private double z;

    // Konstruktor domyślny zerujący punkty
    public Space3D() {
        super(); // Wywołanie konstruktora klasy Space2D (ustawia x = 0, y = 0)
        this.z = 0;
    }

    // Konstruktor przypisujący wartości punktom
    public Space3D(double x, double y, double z) {
        super(x, y); // Wywołanie konstruktora klasy Space2D
        this.z = z;
    }

    // Metoda obliczająca obwód trójkąta powstałego z trzech punktów w 3D
    public static double trianglePerimeter(Space3D a, Space3D b, Space3D c) {
        return a.distance(b) + b.distance(c) + c.distance(a);
    }

    // Metoda obliczająca pole trójkąta w przestrzeni 3D (wzór Herona)
    public static double triangleArea(Space3D a, Space3D b, Space3D c) {
        double sideA = a.distance(b);
        double sideB = b.distance(c);
        double sideC = c.distance(a);
        double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    // Gettery i settery
    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    // Metoda wyświetlająca wartości punktów
    public void display() {
        System.out.println("Punkt: (" + getX() + ", " + getY() + ", " + z + ")");
    }

    // Metoda obliczająca odległość między dwoma punktami w 3D
    public double distance(Space3D other) {
        return Math.sqrt(
            Math.pow(this.getX() - other.getX(), 2) +
            Math.pow(this.getY() - other.getY(), 2) +
            Math.pow(this.z - other.z, 2)
        );
    }

    // Metoda obliczająca odległość punktu od (0,0,0)
    public double distanceFromOrigin() {
        return Math.sqrt(getX() * getX() + getY() * getY() + z * z);
    }

    // Metoda toString()
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ", " + z + ")";
    }
}
