package uz.zgora.selenium.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uz.zgora.selenium.base.BaseTest;
import uz.zgora.selenium.pages.UZHomePage;

@DisplayName("Strona UZ - wyszukiwanie w serwisie")
class UZSearchTest extends BaseTest {

    @Test
    @DisplayName("Tytul strony glownej zawiera 'Uniwersytet'")
    void homePageHasUniversityTitle() {
        UZHomePage page = new UZHomePage(getDriver()).openHome();
        assertThat(page.title()).containsIgnoringCase("uniwersytet");
    }

    @Test
    @DisplayName("Wyszukiwanie 'rekrutacja' zwraca strone wynikow zawierajaca to slowo")
    void searchingReturnsRelevantResults() {
        UZHomePage page = new UZHomePage(getDriver()).openHome();
        assumeTrue(page.hasSearchField(),
                "Strona UZ nie udostepnia pola wyszukiwania - test pomijany");

        page.searchFor("rekrutacja");

        assertThat(page.pageBodyText())
                .as("strona wynikow wspomina o rekrutacji")
                .containsIgnoringCase("rekrutacja");
    }
}
