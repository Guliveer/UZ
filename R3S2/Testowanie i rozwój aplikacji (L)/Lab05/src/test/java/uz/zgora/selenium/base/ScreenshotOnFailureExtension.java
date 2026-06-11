package uz.zgora.selenium.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uz.zgora.selenium.config.TestConfig;

/**
 * Robi zrzut ekranu po nieudanym tescie i zapisuje go w {@code target/screenshots}.
 * Wykorzystuje {@link TestWatcher} z JUnit 5 - hook wywolywany tylko dla niepowodzen.
 */
public class ScreenshotOnFailureExtension implements TestWatcher {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotOnFailureExtension.class);
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object instance = context.getTestInstance().orElse(null);
        if (!(instance instanceof BaseTest baseTest)) {
            return;
        }
        WebDriver driver = baseTest.getDriver();
        if (driver == null) {
            return;
        }
        try {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Path dir = Paths.get(TestConfig.get().screenshotsDir());
            Files.createDirectories(dir);
            String filename = "%s_%s.png".formatted(
                    LocalDateTime.now().format(TS),
                    context.getDisplayName().replaceAll("[^a-zA-Z0-9_-]", "_"));
            Path file = dir.resolve(filename);
            Files.write(file, png);
            log.error("Test '{}' nie powiodl sie. Zrzut ekranu: {}",
                    context.getDisplayName(), file.toAbsolutePath());
        } catch (IOException e) {
            log.warn("Nie udalo sie zapisac zrzutu ekranu", e);
        }
    }
}
