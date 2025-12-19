package zad01.p1;

/**
 * Fabryka konkretna dla trybu jasnego
 */
public class LightGUIFactory implements GUIFactory {
    @Override
    public String getName() {
        return "Light";
    }

    @Override
    public Window createWindow() {
        return new LightWindow();
    }

    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public TextField createTextField() {
        return new LightTextField();
    }
}