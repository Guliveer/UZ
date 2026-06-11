package uz.zgora.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import uz.zgora.selenium.config.TestConfig;

/**
 * Strona glowna Uniwersytetu Zielonogorskiego - https://www.uz.zgora.pl/
 */
public class UZHomePage extends BasePage {

    private static final By SEARCH_INPUT = By.cssSelector(
            "form.searchForm input[name='query'], "
                    + "input[type='search'], input[name*='search'], input[id*='search'], input[name='s']");
    private static final By SEARCH_TOGGLE = By.cssSelector(
            ".block-searchadvanced [data-toggle='dropdown']");
    private static final By BODY = By.tagName("body");

    public UZHomePage(WebDriver driver) {
        super(driver);
    }

    public UZHomePage openHome() {
        open(TestConfig.get().url("app.uz.url"));
        wait.until(ExpectedConditions.presenceOfElementLocated(BODY));
        return this;
    }

    public boolean hasSearchField() {
        return isPresent(SEARCH_INPUT);
    }

    public UZHomePage searchFor(String query) {
        if (isPresent(SEARCH_TOGGLE)) {
            clickViaJs(SEARCH_TOGGLE);
        }
        type(SEARCH_INPUT, query);
        waitVisible(SEARCH_INPUT).sendKeys(Keys.ENTER);
        wait.until(d -> !d.getCurrentUrl().equals(TestConfig.get().url("app.uz.url")));
        return this;
    }

    public String pageBodyText() {
        return waitVisible(BODY).getText();
    }
}
