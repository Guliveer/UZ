package uz.zgora.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import uz.zgora.selenium.config.TestConfig;

/**
 * Wspolna implementacja akcji UI dla wszystkich stron.
 * Korzysta wylacznie z explicit waits (zalecana praktyka Selenium 4).
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TestConfig.get().explicitTimeout());
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean isPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void click(By locator) {
        waitClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement el = waitVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected void submitWithEnter(By locator) {
        waitVisible(locator).sendKeys(Keys.ENTER);
    }

    protected void selectByVisibleText(By locator, String text) {
        new Select(waitVisible(locator)).selectByVisibleText(text);
    }

    protected String selectedValue(By locator) {
        return new Select(waitVisible(locator)).getFirstSelectedOption().getDomProperty("value");
    }

    protected void scrollIntoView(By locator) {
        WebElement el = waitVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    /** Klikniecie przez JavaScript - omija sticky headery zaslaniajace cel. */
    protected void clickViaJs(By locator) {
        WebElement el = waitVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected <T> T waitFor(ExpectedCondition<T> condition, Duration timeout) {
        return new WebDriverWait(driver, timeout).until(condition);
    }

    public String currentUrl() {
        return driver.getCurrentUrl();
    }

    public String title() {
        return driver.getTitle();
    }
}
