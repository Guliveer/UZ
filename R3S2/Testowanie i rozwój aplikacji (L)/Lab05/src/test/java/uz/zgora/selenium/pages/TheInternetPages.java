package uz.zgora.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import uz.zgora.selenium.config.TestConfig;

/**
 * Sandbox 'the-internet.herokuapp.com' - testy formularzy, checkboxow i list rozwijanych.
 * Trzymamy 3 podstrony w jednym pliku - kazda jest mala i logicznie powiazana.
 */
public final class TheInternetPages {

    private TheInternetPages() {}

    public static class Login extends BasePage {
        private static final By USERNAME = By.id("username");
        private static final By PASSWORD = By.id("password");
        private static final By SUBMIT   = By.cssSelector("button[type='submit']");
        private static final By FLASH    = By.id("flash");

        public Login(WebDriver driver) {
            super(driver);
        }

        public Login openLoginPage() {
            open(TestConfig.get().url("app.theinternet.url") + "login");
            waitVisible(USERNAME);
            return this;
        }

        public Login submitCredentials(String user, String password) {
            type(USERNAME, user);
            type(PASSWORD, password);
            click(SUBMIT);
            waitVisible(FLASH);
            return this;
        }

        public String flashMessage() {
            return waitVisible(FLASH).getText().trim();
        }

        public boolean isSuccess() {
            String classes = waitVisible(FLASH).getDomAttribute("class");
            return classes != null && classes.contains("success");
        }
    }

    public static class Checkboxes extends BasePage {
        private static final By CHECKBOX_INPUTS = By.cssSelector("#checkboxes input[type='checkbox']");

        public Checkboxes(WebDriver driver) {
            super(driver);
        }

        public Checkboxes openPage() {
            open(TestConfig.get().url("app.theinternet.url") + "checkboxes");
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(CHECKBOX_INPUTS));
            return this;
        }

        private List<WebElement> boxes() {
            return driver.findElements(CHECKBOX_INPUTS);
        }

        public int count() {
            return boxes().size();
        }

        public boolean isChecked(int index) {
            return boxes().get(index).isSelected();
        }

        public Checkboxes setChecked(int index, boolean expected) {
            WebElement cb = boxes().get(index);
            if (cb.isSelected() != expected) {
                cb.click();
            }
            return this;
        }
    }

    public static class Dropdown extends BasePage {
        private static final By DROPDOWN = By.id("dropdown");

        public Dropdown(WebDriver driver) {
            super(driver);
        }

        public Dropdown openPage() {
            open(TestConfig.get().url("app.theinternet.url") + "dropdown");
            waitVisible(DROPDOWN);
            return this;
        }

        public Dropdown select(String visibleText) {
            selectByVisibleText(DROPDOWN, visibleText);
            return this;
        }

        public String selectedValue() {
            return selectedValue(DROPDOWN);
        }
    }
}
