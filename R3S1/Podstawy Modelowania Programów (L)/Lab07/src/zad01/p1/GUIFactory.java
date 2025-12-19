package zad01.p1;

/**
 * Interfejs fabryki abstrakcyjnej dla tworzenia komponentów GUI
 */
public interface GUIFactory {
    String getName();
    Window createWindow();
    Button createButton();
    TextField createTextField();
}