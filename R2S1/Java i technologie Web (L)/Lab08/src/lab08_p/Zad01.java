// Oliwer Pawelski, 24INF-SP/A

// Należy zmodyfikować wcześniej napisany program z listy 5 w taki sposób aby
// wszystkie pola klasy Samochód były dostępne maksymalnie z poziomu pakietu.
// Dodatkowo dodać zmienną Lp. która będzie dostępna tylko w klasie AutoKomis.

package lab08_p;

class Car {
    // Data
    private int productionYear;
    private String brand;
    private String model;
    private double engineCapacity;
    private String bodyType;
    private String engineType;
    private String firstRegistrationDate;
    private double price;


    // Constructor
    public Car() {
        final String text = "Unknown";

        this.brand = text;
        this.model = text;
        this.engineCapacity = 0.0;
        this.bodyType = text;
        this.engineType = text;
        this.productionYear = 0;
        this.firstRegistrationDate = text;
        this.price = 0.0;
    }

    // Methods
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getEngineCapacity() {
        return this.engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getBodyType() {
        return this.bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getEngineType() {
        return this.engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getProductionYear() {
        return this.productionYear;
    }

    public void setProductionYear(int productionYear){
        this.productionYear = productionYear;
    }

    public String getFirstRegistrationDate() {
        return this.firstRegistrationDate;
    }

    public void setFirstRegistrationDate(String firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Zad02
    public boolean getWarranty() {
        int currentYear = 2021;
        int firstRegistrationYear = Integer.parseInt(this.firstRegistrationDate.substring(0, 4));
        int age = currentYear - firstRegistrationYear;

        return age <= 2;
    }

    public void printAllData() {
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Engine capacity: " + this.engineCapacity);
        System.out.println("Body type: " + this.bodyType);
        System.out.println("Engine type: " + this.engineType);
        System.out.println("Production year: " + this.productionYear);
        System.out.println("First registration date: " + this.firstRegistrationDate);
        System.out.println("Price: " + this.price);
        System.out.println();
    }
}

class CarDealership {
    // Data
    private final Car[] cars;
    private final int lp;

    // Constructor
    public CarDealership() {
        this.cars = new Car[10];
        this.lp = 1;
        for (int i = 0; i < 10; i++) {
            this.cars[i] = new Car();
        }
    }

    // Methods
    public void printAllData() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Car " + (i + 1) + ":");
            this.cars[i].printAllData();
        }
    }
}

