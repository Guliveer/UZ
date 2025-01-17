// Oliwer Pawelski, 24INF-SP/A

// Używając bibliotekę Java Mail napisz program, który wysyła wiadomości email.
// Do tego celu należy wykorzystać studenckie konto pocztowe UZ.

package lab11_p;

// Jakarta Mail (1.6.7) & Activation (1.2.2)
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Console;
import java.util.Properties;

public class Zad04 {
    public static void main(String[] args) {
        Console console = System.console();

        if (console == null) {
            System.err.println("Nie można uzyskać dostępu do konsoli. Uruchom program w terminalu/wierszu poleceń.");
            return;
        }

        // Pobranie danych logowania
        // Login
        String login = console.readLine("Podaj login (nr indeksu): ");
        // Hasło
        char[] passwordArray = console.readPassword("Podaj hasło: ");
        String password = new String(passwordArray);


        // Konfiguracja właściwości dla serwera SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "poczta.stud.uz.zgora.pl");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.ssl.checkserveridentity", true);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Utworzenie sesji z uwierzytelnieniem
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login + "@stud.uz.zgora.pl", password);
            }
        });

        try {
            // Tworzenie wiadomości e-mail
            String recipient = console.readLine("Podaj adres odbiorcy: ");
            String subject = console.readLine("Podaj temat wiadomości: ");
            String body = console.readLine("Podaj treść wiadomości: ");

            body += "\n---\nSent using a program created by Oliwer Pawelski\ngithub.com/Guliveer"; // additional footer

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(login + "@stud.uz.zgora.pl"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);

            // Wysyłanie wiadomości
            Transport.send(message);
            System.out.println("Wiadomość została wysłana pomyślnie!");

        } catch (MessagingException e) {
            System.err.println("Wystąpił błąd podczas wysyłania wiadomości: " + e.getMessage());
        }
    }
}