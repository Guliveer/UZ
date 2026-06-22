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
        assertThat(page.hasCalendarsLink()).as("link do kalendarzy").isTrue();
        assertThat(page.hasPrivacyLink()).as("link polityki prywatnosci").isTrue();
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

    @Test
    @DisplayName("Strona Plan nauczycieli prezentuje alfabet z co najmniej 20 literami")
    void teacherPageShowsAlphabetNavigation() {
        PlanUZPage page = new PlanUZPage(getDriver())
                .openHome()
                .goToTeacherPlan();

        assertThat(page.countAlphabetLetters())
                .as("liczba liter w alfabecie nawigacyjnym")
                .isGreaterThanOrEqualTo(20);
        assertThat(page.h3Heading())
                .containsIgnoringCase("nauczyciel");
    }

    @Test
    @DisplayName("Wyszukiwarka nauczyciela filtruje liste na biezaco po wpisaniu frazy")
    void teacherSearchFiltersList() {
        PlanUZPage page = new PlanUZPage(getDriver())
                .openHome()
                .goToTeacherPlan()
                .goToTeacherSearch()
                .searchTeacher("Kowalski");

        assertThat(page.currentUrl())
                .as("URL pozostaje na stronie wyszukiwarki (brak GET redirect)")
                .contains("nauczyciel_lista_szukaj.php");
        assertThat(page.visibleTeacherSearchResults())
                .as("widoczne wyniki wyszukiwania zawieraja fraze Kowalski")
                .isNotEmpty()
                .allSatisfy(name -> assertThat(name).containsIgnoringCase("Kowalski"));
    }

    @Test
    @DisplayName("Plan grup: lista kierunkow zawiera co najmniej 10 kierunkow")
    void groupsPageListsCourses() {
        PlanUZPage page = new PlanUZPage(getDriver())
                .openHome()
                .goToGroupsPlan();

        assertThat(page.countGroupCourseItems())
                .as("liczba kierunkow na liscie grup")
                .isGreaterThanOrEqualTo(10);
        assertThat(page.allGroupCourseNames())
                .as("lista nazw kierunkow")
                .isNotEmpty()
                .allSatisfy(name -> assertThat(name).isNotBlank());
    }

    @Test
    @DisplayName("Plan grup: wybor kierunku pokazuje liste grup tego kierunku")
    void selectingCourseShowsGroupList() {
        PlanUZPage page = new PlanUZPage(getDriver())
                .openHome()
                .goToGroupsPlan()
                .selectGroupCourse("Filozofia");

        assertThat(page.h3Heading())
                .as("naglowek strony grup kierunku")
                .containsIgnoringCase("Filozofia");
        assertThat(page.countGroupPlanLinks())
                .as("liczba grup dla kierunku Filozofia")
                .isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("Plan grup: wybor grupy otwiera strone z planem zajec tej grupy")
    void selectingGroupOpensSchedule() {
        PlanUZPage page = new PlanUZPage(getDriver())
                .openHome()
                .goToGroupsPlan()
                .selectGroupCourse("Filozofia")
                .selectFirstGroupFromList();

        assertThat(page.currentUrl())
                .as("URL planu grupy zawiera grupy_plan.php")
                .contains("grupy_plan.php");
        assertThat(page.mainContentText())
                .as("strona planu grupy ma tresci")
                .isNotBlank();
    }

    @Test
    @DisplayName("Plan sal: lista budynkow zawiera linki do Google Maps")
    void roomsPageHasMapLinks() {
        PlanUZPage page = new PlanUZPage(getDriver())
                .openHome()
                .goToRoomsPlan();

        assertThat(page.countMapLinks())
                .as("liczba linkow do Google Maps")
                .isGreaterThanOrEqualTo(5);
        assertThat(page.h3Heading())
                .containsIgnoringCase("sal");
    }

    @Test
    @DisplayName("Plan sal: wybor budynku A-0 pokazuje sale tego budynku")
    void selectingBuildingShowsRooms() {
        PlanUZPage page = new PlanUZPage(getDriver())
                .openHome()
                .goToRoomsPlan()
                .selectBuildingRoom("A-0");

        assertThat(page.currentUrl())
                .as("URL listy sal zawiera ID budynku")
                .contains("sale_lista_sal_budynku.php");
        assertThat(page.h3Heading())
                .containsIgnoringCase("A-0");
    }

    @Test
    @DisplayName("Kalendarze: strona wyswietla liste dostepnych kalendarzy")
    void calendarsPageListsCalendars() {
        PlanUZPage page = new PlanUZPage(getDriver())
                .openHome()
                .goToCalendars();

        assertThat(page.currentUrl())
                .contains("kalendarze_lista.php");
        assertThat(page.h3Heading())
                .as("naglowek strony kalendarzy")
                .containsIgnoringCase("kalendarz");
        assertThat(page.mainContentText())
                .as("strona zawiera informacje o semestrze")
                .containsIgnoringCase("semestr");
    }
}
