package uz.zgora.selenium.pages;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import uz.zgora.selenium.config.TestConfig;

/**
 * Polska Wikipedia - test wyszukiwania, naglowka i nawigacji wstecz.
 *
 * Uwaga: zamiast przechodzic przez strone glowna i borykac sie z Vector-2022
 * (gdzie pole #searchInput bywa ukryte do kliknieciu lupki), uzywamy
 * publicznego endpointu Specjalna:Szukaj?search= - to natywne API wyszukiwania.
 */
public class WikipediaPage extends BasePage {

    private static final By FIRST_HEADING = By.id("firstHeading");
    private static final By BODY_CONTENT_LINK =
            By.cssSelector("#bodyContent p a[href^='/wiki/']:not([class*='new']):not([class*='mw-redirect'])");

    public WikipediaPage(WebDriver driver) {
        super(driver);
    }

    public WikipediaPage openHome() {
        open(TestConfig.get().url("app.wikipedia.url"));
        wait.until(ExpectedConditions.titleContains("Wikipedia"));
        return this;
    }

    public WikipediaPage searchFor(String query) {
        String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8);
        open("https://pl.wikipedia.org/w/index.php?search=" + encoded);
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_HEADING));
        return this;
    }

    public String headingText() {
        return waitVisible(FIRST_HEADING).getText();
    }

    public WikipediaPage clickFirstWikiLink() {
        scrollIntoView(BODY_CONTENT_LINK);
        String before = title();
        click(BODY_CONTENT_LINK);
        wait.until(d -> !d.getTitle().equals(before));
        return this;
    }

    public WikipediaPage goBack() {
        String before = title();
        driver.navigate().back();
        wait.until(d -> !d.getTitle().equals(before));
        return this;
    }
}
