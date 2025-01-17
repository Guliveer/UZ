package lab11_p;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Properties;

public class Zad04GUI {

    private static String login;
    private static String password;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Zad04GUI::showLoginWindow);
    }

    private static void showLoginWindow() {
        JFrame loginFrame = new JFrame("Logowanie do poczty UZ");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(500, 300);
        loginFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loginFrame.add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel loginLabel = new JLabel("Login (nr indeksu):");
        JTextField loginField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Hasło:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Zaloguj");
        JLabel statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(loginLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(loginField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        gbc.gridy = 3;
        mainPanel.add(statusLabel, gbc);

        // Obsługa klawisza Enter w polach tekstowych
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick(); // Symuluje kliknięcie przycisku "Zaloguj"
                }
            }
        };

        // Przypisanie listenera do pól tekstowych
        loginField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);

        loginButton.addActionListener(e -> {
            login = loginField.getText() + "@stud.uz.zgora.pl";
            password = new String(passwordField.getPassword());

            if (login.isEmpty() || password.isEmpty()) {
                statusLabel.setText("Wypełnij wszystkie pola!");
                return;
            }

            // authenticate session - if a user is not authenticated, it will throw an exception
            try {
                Session session = createMailSession(login, password);
                session.getTransport("smtp").connect();
            } catch (MessagingException ex) {
                statusLabel.setText("Błąd logowania: " + ex.getMessage());
                return;
            }

            loginFrame.dispose();
            showEmailWindow();
        });

        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    private static void showEmailWindow() {
        JFrame emailFrame = new JFrame("Wysyłanie e-maila");
        emailFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        emailFrame.setSize(600, 500);
        emailFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        emailFrame.add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel recipientLabel = new JLabel("Adres odbiorcy:");
        JTextField recipientField = new JTextField(30);

        JLabel subjectLabel = new JLabel("Temat:");
        JTextField subjectField = new JTextField(30);

        JLabel bodyLabel = new JLabel("Treść wiadomości:");
        JTextArea bodyArea = new JTextArea(8, 30);
        JScrollPane bodyScrollPane = new JScrollPane(bodyArea);

        JButton sendButton = new JButton("Wyślij");
        JLabel statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(recipientLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(recipientField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(subjectLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(subjectField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(bodyLabel, gbc);

        gbc.gridy = 3;
        mainPanel.add(bodyScrollPane, gbc);

        gbc.gridy = 4;
        mainPanel.add(sendButton, gbc);

        gbc.gridy = 5;
        mainPanel.add(statusLabel, gbc);

        sendButton.addActionListener(e -> {
            String recipient = recipientField.getText();
            String subject = subjectField.getText();
            String body = bodyArea.getText();
            // add footer to the email body

            if (recipient.isEmpty() || subject.isEmpty() || body.isEmpty()) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Wypełnij wszystkie pola!");
                return;
            }

            body += "\n---\nSent using a program created by Oliwer Pawelski\ngithub.com/Guliveer"; // additional footer
            try {
                sendEmail(recipient, subject, body);
                statusLabel.setForeground(Color.getHSBColor(0.3f, 0.75f, 0.5f)); // green (not so vibrant)
                statusLabel.setText("Wiadomość wysłana!");
            } catch (Exception ex) {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Błąd: " + ex.getMessage());
            }
        });

        emailFrame.setLocationRelativeTo(null);
        emailFrame.setVisible(true);
    }

    private static void sendEmail(String recipient, String subject, String body) throws MessagingException {
        Session session = createMailSession(login, password);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(login));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }

    private static Session createMailSession(String login, String password) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "poczta.stud.uz.zgora.pl");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.ssl.checkserveridentity", true);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });
    }
}