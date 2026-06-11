package uz.zgora.selenium.config;

import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Fabryka WebDrivera. Wybor przegladarki sterowany flaga {@code -Dbrowser=chrome|firefox|edge}.
 * WebDriverManager pobiera odpowiedniego drivera dla zainstalowanej przegladarki.
 */
public final class WebDriverFactory {

    private WebDriverFactory() {}

    public static WebDriver create() {
        TestConfig cfg = TestConfig.get();
        WebDriver driver = switch (cfg.browser()) {
            case "firefox" -> firefox(cfg.headless());
            case "edge"    -> edge(cfg.headless());
            default        -> chrome(cfg.headless());
        };

        driver.manage().timeouts().pageLoadTimeout(cfg.pageLoadTimeout());
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().window().setSize(new Dimension(cfg.windowWidth(), cfg.windowHeight()));
        return driver;
    }

    private static WebDriver chrome(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--lang=pl-PL", "--disable-notifications", "--disable-infobars");
        if (headless) {
            options.addArguments("--headless=new", "--window-size=1366,768");
        }
        return new ChromeDriver(options);
    }

    private static WebDriver firefox(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addPreference("intl.accept_languages", "pl-PL,pl");
        if (headless) {
            options.addArguments("-headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver edge(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--lang=pl-PL");
        if (headless) {
            options.addArguments("--headless=new");
        }
        return new EdgeDriver(options);
    }

    public static String describeBrowser() {
        return TestConfig.get().browser().toUpperCase(Locale.ROOT)
                + (TestConfig.get().headless() ? " (headless)" : "");
    }
}
