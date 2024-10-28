// Oliwer Pawelski, 24INF-SP/A

// Należy poprawić program, tak aby działał poprawnie

package lab05_p;

class Point { // - public
    public int x = 0;
    public int y = 0;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Rectangle {
    Point origin;
    private int width = 0; // public -> private
    private int height = 0; // public -> private
    // four constructors
    public Rectangle() {
        this.origin = new Point(0, 0); // + this
    }

    public Rectangle(Point p) {
        this.origin = p; // + this
    }

    public Rectangle(int w, int h) {
        this(new Point(0, 0), w, h);
    }

    public Rectangle(Point p, int w, int h) {
        this.origin = p; // + this
        this.width = w; // + this
        this.height = h; // + this
    }

    public void move(int x, int y) {
        this.origin.x = x; // + this
        this.origin.y = y; // + this
    }

    public int area() {
        return this.width * this.height;// + this (2x)
    }

    public int getWidth() { // Nowa metoda
        return this.width;
    }

    public void setWidth(int width) { // Nowa metoda
        this.width = width;
    }

    public int getHeight() { // Nowa metoda
        return this.height;
    }

    public void setHeight(int height) { // Nowa metoda
        this.height = height;
    }
}

class Zadanie_1 { // - public
    public static void main(String[] args) {
        Rectangle myRect = new Rectangle(); // + = new Rectangle()
        myRect.setWidth(40); // myRect.width = 40; -> myRect.setWidth(40);
        myRect.setHeight(50); // myRect.height = 50; -> myRect.setHeight(50);
        System.out.println("Obszar kwadratu: " + myRect.area());
    }
}