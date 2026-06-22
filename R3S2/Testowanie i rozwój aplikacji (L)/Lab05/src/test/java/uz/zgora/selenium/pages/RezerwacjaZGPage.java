package uz.zgora.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import uz.zgora.selenium.config.TestConfig;

/**
 * System rezerwacji obiektow Zielona Gora - https://rezerwacja.zielona-gora.pl/
 * Strona to landing rezerwacji do Biura Rejestracji Pojazdow z widgetem bookero.
 *
 * UWAGA: zadna metoda w tej klasie nie zatwierdza rezerwacji.
 * Granica bezpieczenstwa: przycisk .submit-button-step-1 ("Przejdz do podsumowania") nie jest nigdy klikany.
 */
public class RezerwacjaZGPage extends BasePage {

    private static final By MAIN_HEADING    = By.cssSelector("h1");
    private static final By BOOKING_WIDGET  = By.cssSelector("[id^='bookero-']");
    private static final By FOOTER_LINKS    = By.cssSelector("footer a.footer-link");
    private static final By REGULAMIN_LINK  = By.partialLinkText("Regulamin");
    private static final By POLITYKA_LINK   = By.partialLinkText("Polityka prywatno");
    private static final By FOOTER_BRAND    = By.cssSelector("footer .logo img");
    private static final By COOKIE_OVERLAY  = By.cssSelector(".info");

    private static final By BOOKERO_PLUGIN       = By.id("bookero-plugin");
    private static final By BOOKERO_FORM_HEADER  = By.cssSelector(".bookero-plugin-header-add");
    private static final By SERVICE_MULTISELECT  = By.cssSelector(".multiselect.bookero-plugin-services-list-styled-all");
    private static final By SERVICE_SINGLE_LABEL = By.cssSelector(".multiselect__single");
    private static final By SERVICE_OPTIONS      = By.cssSelector(".multiselect__option > span");
    private static final By TERM_HEADING         = By.cssSelector(".bookero-plugin-form-heading-term");
    private static final By CALENDAR_NAV_MONTH   = By.cssSelector(".calendar-nav-month");
    private static final By CALENDAR_NAV_NEXT    = By.cssSelector(".calendar-nav-next:not(.is-hidden)");
    private static final By DATES_SECTION        = By.cssSelector(".dates-section");
    private static final By DATES_ERROR          = By.cssSelector(".dates-section-load-error");
    private static final By SUBMIT_BUTTON        = By.cssSelector(".submit-button-step-1");
    private static final By REQUIRED_NOTICE      = By.cssSelector(".required-fields-notice");
    private static final By BOOKERO_FOOTER       = By.cssSelector(".bookero-plugin-footer");
    private static final By PARAMS_SECTION       = By.cssSelector(".params-section");

    public RezerwacjaZGPage(WebDriver driver) {
        super(driver);
    }

    public RezerwacjaZGPage openHome() {
        open(TestConfig.get().url("app.rezerwacja.url"));
        waitVisible(MAIN_HEADING);
        return this;
    }

    public RezerwacjaZGPage waitForBookeroPlugin() {
        wait.until(ExpectedConditions.presenceOfElementLocated(BOOKERO_PLUGIN));
        wait.until(d -> {
            List<WebElement> plugins = d.findElements(BOOKERO_PLUGIN);
            if (plugins.isEmpty()) return false;
            return "true".equals(plugins.get(0).getDomAttribute("data-is-initialized"));
        });
        return this;
    }

    public RezerwacjaZGPage dismissCookieBanner() {
        ((JavascriptExecutor) driver).executeScript(
                "var i = document.querySelector('.info'); if(i) i.style.display='none';");
        return this;
    }

    public RezerwacjaZGPage openServiceDropdown() {
        dismissCookieBanner();
        By tagsSelector = By.cssSelector(".multiselect__tags");
        scrollIntoView(tagsSelector);
        waitClickable(tagsSelector).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".multiselect__content-wrapper")));
        return this;
    }

    public RezerwacjaZGPage selectService(String partialName) {
        openServiceDropdown();
        // Wrapper jest juz widoczny po openServiceDropdown().
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(SERVICE_OPTIONS));
        List<WebElement> spans = driver.findElements(SERVICE_OPTIONS);
        WebElement match = spans.stream()
                .filter(s -> s.getText().contains(partialName))
                .findFirst()
                .orElseThrow(() -> new org.openqa.selenium.NoSuchElementException("Brak opcji: " + partialName));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'nearest'});", match);
        match.click();
        String prefix = partialName.substring(0, Math.min(10, partialName.length()));
        wait.until(d -> {
            List<WebElement> labels = d.findElements(SERVICE_SINGLE_LABEL);
            return !labels.isEmpty() && labels.get(0).getText().contains(prefix);
        });
        return this;
    }

    public boolean isBookeroPluginInitialized() {
        List<WebElement> plugins = driver.findElements(BOOKERO_PLUGIN);
        if (plugins.isEmpty()) return false;
        return "true".equals(plugins.get(0).getDomAttribute("data-is-initialized"));
    }

    public boolean hasCalendarDatesError() {
        return isPresent(DATES_ERROR);
    }

    public boolean hasSubmitButton() {
        return isPresent(SUBMIT_BUTTON);
    }

    public boolean hasRequiredFieldsNotice() {
        return isPresent(REQUIRED_NOTICE);
    }

    public boolean hasCalendarNavMonth() {
        return isPresent(CALENDAR_NAV_MONTH);
    }

    public boolean hasCalendarNextButton() {
        return isPresent(CALENDAR_NAV_NEXT);
    }

    public String mainHeading() {
        return waitVisible(MAIN_HEADING).getText();
    }

    public String bookeroFormHeader() {
        return waitVisible(BOOKERO_FORM_HEADER).getText();
    }

    public String selectedServiceLabel() {
        return waitVisible(SERVICE_SINGLE_LABEL).getText();
    }

    public String bookeroFooterText() {
        return waitVisible(BOOKERO_FOOTER).getText();
    }

    public String mainContentText() {
        return waitVisible(By.tagName("body")).getText();
    }

    public String termHeadingText() {
        return waitVisible(TERM_HEADING).getText();
    }

    public List<String> serviceOptionTexts() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".multiselect__content-wrapper")));
        return driver.findElements(SERVICE_OPTIONS).stream()
                .map(WebElement::getText)
                .filter(t -> !t.isBlank())
                .toList();
    }

    public int countFooterLinks() {
        return driver.findElements(FOOTER_LINKS).size();
    }

    public String firstFooterLinkHref() {
        scrollIntoView(FOOTER_LINKS);
        return wait.until(ExpectedConditions.presenceOfElementLocated(FOOTER_LINKS)).getDomProperty("href");
    }

    public String regulaminLinkHref() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(REGULAMIN_LINK)).getDomProperty("href");
    }

    public String politykaLinkHref() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(POLITYKA_LINK)).getDomProperty("href");
    }

    public boolean regulaminOpensInNewTab() {
        return "_blank".equals(
                wait.until(ExpectedConditions.presenceOfElementLocated(REGULAMIN_LINK))
                        .getDomAttribute("target"));
    }

    public boolean politykaOpensInNewTab() {
        return "_blank".equals(
                wait.until(ExpectedConditions.presenceOfElementLocated(POLITYKA_LINK))
                        .getDomAttribute("target"));
    }

    public RezerwacjaZGPage hoverRegulaminLink() {
        WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(FOOTER_LINKS));
        scrollIntoView(FOOTER_LINKS);
        new Actions(driver).moveToElement(link).perform();
        return this;
    }

    public RezerwacjaZGPage openRegulaminInNewTab() {
        scrollIntoView(REGULAMIN_LINK);
        click(REGULAMIN_LINK);
        wait.until(d -> d.getWindowHandles().size() > 1);
        return this;
    }
}
