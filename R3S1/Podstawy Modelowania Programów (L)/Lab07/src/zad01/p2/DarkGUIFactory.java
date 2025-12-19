package zad01.p2;

/**
 * Fabryka konkretna dla trybu ciemnego
 */
public class DarkGUIFactory implements GUIFactory {
    @Override
    public String getName() {
        return "Dark";
    }

    @Override
    public Window createWindow() {
        return new DarkWindow();
    }

    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public TextField createTextField() {
        return new DarkTextField();
    }
}