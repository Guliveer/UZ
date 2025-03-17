package lab02_p.Zad01;

public class SpaceTest {
    public static void main(String[] args) {
        Space2D p1 = new Space2D(1, 2);
        Space2D p2 = new Space2D(4, 6);
        Space2D p3 = new Space2D(7, 2);

        System.out.println("Odległość między p1 a p2: " + p1.distance(p2));
        System.out.println("Odległość p1 od (0,0): " + p1.distanceFromOrigin());
        System.out.println("Obwód trójkąta: " + Space2D.trianglePerimeter(p1, p2, p3));
        System.out.println("Pole trójkąta: " + Space2D.triangleArea(p1, p2, p3));

        Space3D p4 = new Space3D(1, 2, 3);
        Space3D p5 = new Space3D(4, 6, 5);
        Space3D p6 = new Space3D(7, 2, 8);

        System.out.println("Odległość między p4 a p5: " + p4.distance(p5));
        System.out.println("Odległość p4 od (0,0,0): " + p4.distanceFromOrigin());
        System.out.println("Obwód trójkąta: " + Space3D.trianglePerimeter(p4, p5, p6));
        System.out.println("Pole trójkąta: " + Space3D.triangleArea(p4, p5, p6));
    }
}
