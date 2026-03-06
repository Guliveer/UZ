package zad01;

public class Main {
    boolean IsValid (String number) {
        if (number.length() != 11) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
