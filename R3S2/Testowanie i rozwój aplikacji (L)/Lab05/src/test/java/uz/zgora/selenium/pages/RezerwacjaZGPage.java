package uz.zgora.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import uz.zgora.selenium.config.TestConfig;

/**
 * System rezerwacji obiektow Zielona Gora - https://rezerwacja.zielona-gora.pl/
 * Strona to landing rezerwacji do Biura Rejestracji Pojazdow z widgetem bookero.
 */
public class RezerwacjaZGPage extends BasePage {

    private static final By MAIN_HEADING = By.cssSelector("h1");
    private static final By BOOKING_WIDGET = By.cssSelector("[id^='bookero-']");
    private static final By FOOTER_LINKS = By.cssSelector("footer a.footer-link");
    private static final By POLITYKA_LINK = By.partialLinkText("Polityka prywatnosci");
    private static final By REGULAMIN_LINK = By.partialLinkText("Regulamin");

    public RezerwacjaZGPage(WebDriver driver) {
        super(driver);
    }

    public RezerwacjaZGPage openHome() {
        open(TestConfig.get().url("app.rezerwacja.url"));
        waitVisible(MAIN_HEADING);
        return this;
    }

    public String mainHeading() {
        return waitVisible(MAIN_HEADING).getText();
    }

    public boolean hasBookingWidget() {
        return isPresent(BOOKING_WIDGET);
    }

    public int countFooterLinks() {
        return driver.findElements(FOOTER_LINKS).size();
    }

    public RezerwacjaZGPage hoverRegulaminLink() {
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(FOOTER_LINKS));
        scrollIntoView(FOOTER_LINKS);
        new Actions(driver).moveToElement(link).perform();
        return this;
    }

    public String firstFooterLinkHref() {
        scrollIntoView(FOOTER_LINKS);
        return wait.until(ExpectedConditions.presenceOfElementLocated(FOOTER_LINKS)).getDomProperty("href");
    }

    public RezerwacjaZGPage openRegulaminInNewTab() {
        scrollIntoView(REGULAMIN_LINK);
        click(REGULAMIN_LINK);
        wait.until(d -> d.getWindowHandles().size() > 1);
        return this;
    }
}
