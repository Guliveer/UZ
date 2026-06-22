package uz.zgora.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import uz.zgora.selenium.config.TestConfig;

/**
 * Plan zajec Uniwersytetu Zielonogorskiego - http://www.plan.uz.zgora.pl/
 */
public class PlanUZPage extends BasePage {

    private static final By LINK_NAUCZYCIELE  = By.linkText("Plan nauczycieli");
    private static final By LINK_GRUPY        = By.linkText("Plan grup");
    private static final By LINK_SALE         = By.linkText("Plan sal");
    private static final By LINK_KALENDARZE   = By.linkText("Kalendarze");
    private static final By LINK_SZUKAJ       = By.linkText("Szukaj nauczyciela");
    private static final By TEACHER_SEARCH    = By.id("teacher-search");
    private static final By LISTA_GRUP_ITEMS  = By.cssSelector("li.lista-grup-item");
    private static final By GRUPY_PLAN_LINKS  = By.cssSelector("td > a[href*='grupy_plan.php']");
    private static final By BODY              = By.cssSelector("body");
    private static final By H3_HEADING        = By.cssSelector("h3, H3");
    private static final By CALENDAR_LETTERS  = By.cssSelector("td > a[href*='nauczyciel_lista_litera']");
    private static final By MAP_LINKS         = By.cssSelector("a[href*='google.com/maps']");
    private static final By PRIVACY_LINK      = By.linkText("Polityka prywatności");

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

    public PlanUZPage goToGroupsPlan() {
        click(LINK_GRUPY);
        waitVisible(BODY);
        return this;
    }

    public PlanUZPage goToRoomsPlan() {
        click(LINK_SALE);
        waitVisible(BODY);
        return this;
    }

    public PlanUZPage goToCalendars() {
        click(LINK_KALENDARZE);
        waitVisible(BODY);
        return this;
    }

    public PlanUZPage goToTeacherSearch() {
        click(LINK_SZUKAJ);
        waitVisible(TEACHER_SEARCH);
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

    public PlanUZPage selectGroupCourse(String courseName) {
        By locator = By.partialLinkText(courseName);
        scrollIntoView(locator);
        clickViaJs(locator);
        waitVisible(BODY);
        return this;
    }

    public PlanUZPage selectFirstGroupFromList() {
        wait.until(ExpectedConditions.presenceOfElementLocated(GRUPY_PLAN_LINKS));
        WebElement firstLink = driver.findElements(GRUPY_PLAN_LINKS).get(0);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", firstLink);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", firstLink);
        waitVisible(BODY);
        return this;
    }

    public PlanUZPage searchTeacher(String query) {
        type(TEACHER_SEARCH, query);
        wait.until(d -> {
            List<WebElement> visible = d.findElements(By.cssSelector("#search_table tr:not([style*='display: none'])"));
            return !visible.isEmpty();
        });
        return this;
    }

    public PlanUZPage selectBuildingRoom(String buildingId) {
        click(By.linkText(buildingId));
        waitVisible(BODY);
        return this;
    }

    public PlanUZPage selectFirstRoomFromBuilding() {
        By rooms = By.cssSelector("td > a[href*='sale_plan.php']");
        wait.until(ExpectedConditions.presenceOfElementLocated(rooms));
        clickViaJs(rooms);
        waitVisible(BODY);
        return this;
    }

    public boolean hasGroupsLink() {
        return isPresent(LINK_GRUPY);
    }

    public boolean hasRoomsLink() {
        return isPresent(LINK_SALE);
    }

    public boolean hasCalendarsLink() {
        return isPresent(LINK_KALENDARZE);
    }

    public boolean hasTeacherSearchInput() {
        return isPresent(TEACHER_SEARCH);
    }

    public boolean hasPrivacyLink() {
        return isPresent(PRIVACY_LINK);
    }

    public int countAlphabetLetters() {
        return driver.findElements(CALENDAR_LETTERS).size();
    }

    public int countGroupCourseItems() {
        return driver.findElements(LISTA_GRUP_ITEMS).size();
    }

    public int countGroupPlanLinks() {
        return driver.findElements(GRUPY_PLAN_LINKS).size();
    }

    public int countMapLinks() {
        return driver.findElements(MAP_LINKS).size();
    }

    public String h3Heading() {
        return waitVisible(H3_HEADING).getText();
    }

    public String mainContentText() {
        return waitVisible(By.cssSelector(".main, body")).getText();
    }

    public List<String> allGroupCourseNames() {
        return driver.findElements(LISTA_GRUP_ITEMS).stream()
                .map(el -> el.findElement(By.tagName("a")).getText())
                .toList();
    }

    public List<String> visibleTeacherSearchResults() {
        return driver.findElements(By.cssSelector("#search_table td > a")).stream()
                .filter(el -> {
                    WebElement tr = (WebElement) ((org.openqa.selenium.JavascriptExecutor) driver)
                            .executeScript("return arguments[0].closest('tr');", el);
                    String style = tr == null ? "" : (tr.getDomAttribute("style") != null ? tr.getDomAttribute("style") : "");
                    return !style.contains("display: none") && !style.contains("display:none");
                })
                .map(WebElement::getText)
                .filter(t -> !t.isBlank())
                .toList();
    }
}
