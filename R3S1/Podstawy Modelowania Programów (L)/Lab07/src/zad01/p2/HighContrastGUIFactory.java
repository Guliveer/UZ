package zad01.p2;

/**
 * Fabryka konkretna dla trybu wysokiego kontrastu dla niepełnosprawnych
 */
public class HighContrastGUIFactory implements GUIFactory {
    @Override
    public String getName() {
        return "High contrast";
    }

    @Override
    public Window createWindow() {
        return new HighContrastWindow();
    }

    @Override
    public Button createButton() {
        return new HighContrastButton();
    }

    @Override
    public TextField createTextField() {
        return new HighContrastTextField();
    }
}