// Oliwer Pawelski, 24INF-SP/A

// Z podanego zdania należy wyodrębnić zawarte w nim słowa stosując wyrażenie regularne.

package lab08_p;

public class Zad03 {
    public static void main(String[] args) {
        System.out.println("Podaj zdanie: ");
        String sentence = System.console().readLine();
        String[] words = sentence.split("\\s+");
        for (String word : words) {
            System.out.println(word);
        }
    }
}
