package lab02_p.Zad01;

public class Space2D {
    private double x;
    private double y;

    // Konstruktor domyślny zerujący punkty
    public Space2D() {
        this.x = 0;
        this.y = 0;
    }

    // Konstruktor przypisujący wartości punktom
    public Space2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Metoda obliczająca obwód trójkąta
    public static double trianglePerimeter(Space2D a, Space2D b, Space2D c) {
        return a.distance(b) + b.distance(c) + c.distance(a);
    }

    // Metoda obliczająca pole trójkąta (wzór Herona)
    public static double triangleArea(Space2D a, Space2D b, Space2D c) {
        double sideA = a.distance(b);
        double sideB = b.distance(c);
        double sideC = c.distance(a);
        double s = (sideA + sideB + sideC) / 2;

        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    // Gettery i settery
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Metoda wyświetlająca wartości punktów
    public void display() {
        System.out.println("Punkt: (" + x + ", " + y + ")");
    }

    // Metoda obliczająca odległość między dwoma punktami
    public double distance(Space2D other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    // Metoda obliczająca odległość punktu od (0,0)
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    // Metoda toString()
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}