package uz.zgora.selenium.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uz.zgora.selenium.base.BaseTest;
import uz.zgora.selenium.pages.PlanUZPage;

@DisplayName("Plan UZ - nawigacja po podstronach i wybor nauczyciela")
class PlanUZTest extends BaseTest {

    @Test
    @DisplayName("Strona glowna posiada linki do planow grup, nauczycieli i sal")
    void homePageExposesNavigationLinks() {
        PlanUZPage page = new PlanUZPage(getDriver()).openHome();

        assertThat(page.title()).containsIgnoringCase("plan");
        assertThat(page.hasGroupsLink()).as("link do planu grup").isTrue();
        assertThat(page.hasRoomsLink()).as("link do sal").isTrue();
    }

    @Test
    @DisplayName("Wybor nauczyciela: Plan nauczycieli -> B -> dr inz. Jacek Bieganowski")
    void canDrillDownToTeacherSchedule() {
        PlanUZPage page = new PlanUZPage(getDriver())
                .openHome()
                .goToTeacherPlan()
                .goToTeacherStartingWith("B")
                .selectTeacher("dr inż. Jacek Bieganowski");

        assertThat(page.mainContentText())
                .as("plan zawiera wpis seminaryjny")
                .containsIgnoringCase("seminarium");
    }
}
