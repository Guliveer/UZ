package uz.zgora.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import uz.zgora.selenium.config.TestConfig;

/**
 * Plan zajec Uniwersytetu Zielonogorskiego - http://www.plan.uz.zgora.pl/
 */
public class PlanUZPage extends BasePage {

    private static final By LINK_NAUCZYCIELE = By.linkText("Plan nauczycieli");
    private static final By LINK_GRUPY       = By.linkText("Plan grup");
    private static final By LINK_SALE        = By.linkText("Plan sal");
    private static final By BODY             = By.cssSelector("body");

    public PlanUZPage(WebDriver driver) {
        super(driver);
    }

    public PlanUZPage openHome() {
        open(TestConfig.get().url("app.planuz.url"));
        waitVisible(BODY);
        return this;
    }

    public PlanUZPage goToTeacherPlan() {
        click(LINK_NAUCZYCIELE);
        waitVisible(BODY);
        return this;
    }

    public PlanUZPage goToTeacherStartingWith(String letter) {
        click(By.linkText(letter));
        waitVisible(BODY);
        return this;
    }

    public PlanUZPage selectTeacher(String fullName) {
        By teacher = By.linkText(fullName);
        scrollIntoView(teacher);
        clickViaJs(teacher);
        waitVisible(BODY);
        return this;
    }

    public boolean hasGroupsLink() {
        return isPresent(LINK_GRUPY);
    }

    public boolean hasRoomsLink() {
        return isPresent(LINK_SALE);
    }

    public String mainContentText() {
        return waitVisible(By.cssSelector(".main, body")).getText();
    }
}
