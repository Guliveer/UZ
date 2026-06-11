package uz.zgora.selenium.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uz.zgora.selenium.config.TestConfig;
import uz.zgora.selenium.config.WebDriverFactory;

/**
 * Klasa bazowa dla wszystkich testow E2E.
 * Tworzy nowa instancje WebDrivera dla kazdego @Test (izolacja) i sprzata po zakonczeniu.
 */
@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    private WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    void initDriver(TestInfo info) {
        log.info("=== START: {} | przegladarka: {} ===",
                info.getDisplayName(), WebDriverFactory.describeBrowser());
        driver = WebDriverFactory.create();
        wait = new WebDriverWait(driver, TestConfig.get().explicitTimeout());
    }

    @AfterEach
    void quitDriver(TestInfo info) {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                log.warn("Blad przy zamykaniu drivera: {}", e.getMessage());
            }
        }
        log.info("=== KONIEC: {} ===", info.getDisplayName());
    }

    public WebDriver getDriver() {
        return driver;
    }
}
