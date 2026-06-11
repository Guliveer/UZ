package uz.zgora.selenium.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

/**
 * Centralna konfiguracja testow ladowana z {@code test.properties}.
 * Wartosci mozna nadpisac przez {@code -Dklucz=wartosc} na linii komend.
 */
public final class TestConfig {

    private static final TestConfig INSTANCE = new TestConfig();
    private final Properties props = new Properties();

    private TestConfig() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("test.properties")) {
            if (in == null) {
                throw new IllegalStateException("Brak pliku test.properties na classpath");
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Nie udalo sie wczytac test.properties", e);
        }
    }

    public static TestConfig get() {
        return INSTANCE;
    }

    public String url(String key) {
        return getOverridable(key);
    }

    public Duration explicitTimeout() {
        return Duration.ofSeconds(Long.parseLong(getOverridable("timeout.explicit")));
    }

    public Duration pageLoadTimeout() {
        return Duration.ofSeconds(Long.parseLong(getOverridable("timeout.pageLoad")));
    }

    public int windowWidth() {
        return Integer.parseInt(getOverridable("window.width"));
    }

    public int windowHeight() {
        return Integer.parseInt(getOverridable("window.height"));
    }

    public String screenshotsDir() {
        return getOverridable("screenshots.dir");
    }

    public String browser() {
        return System.getProperty("browser", "chrome").toLowerCase();
    }

    public boolean headless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    private String getOverridable(String key) {
        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.isBlank()) {
            return sysProp;
        }
        String value = props.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Brak klucza w konfiguracji: " + key);
        }
        return value;
    }
}
