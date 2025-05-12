// Oliwer Pawelski
// 12.05.2025

class Blotnik {
    private final int serialNumber;

    public Blotnik(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        return "Blotnik{" + serialNumber + '}';
    }
}
