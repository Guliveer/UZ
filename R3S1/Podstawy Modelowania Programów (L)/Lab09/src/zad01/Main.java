package zad01;

public class Main {
    void main() {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controler = new CalculatorController(model, view);
        view.menu();
    }
}
