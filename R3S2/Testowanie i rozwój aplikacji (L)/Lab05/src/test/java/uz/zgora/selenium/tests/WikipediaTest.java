package uz.zgora.selenium.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uz.zgora.selenium.base.BaseTest;
import uz.zgora.selenium.pages.WikipediaPage;

@DisplayName("Wikipedia - wyszukiwanie i nawigacja po artykulach")
class WikipediaTest extends BaseTest {

    @Test
    @DisplayName("Wyszukanie 'Python' otwiera artykul z odpowiednim naglowkiem H1")
    void searchOpensArticle() {
        WikipediaPage page = new WikipediaPage(getDriver())
                .openHome()
                .searchFor("Python");

        assertThat(page.title()).containsIgnoringCase("python");
        assertThat(page.headingText()).containsIgnoringCase("python");
    }

    @Test
    @DisplayName("Klikniecie pierwszego linku wewnetrznego, a potem 'wstecz' wraca do oryginalu")
    void browserBackReturnsToPreviousArticle() {
        WikipediaPage page = new WikipediaPage(getDriver())
                .openHome()
                .searchFor("Python");

        String originalTitle = page.title();
        page.clickFirstWikiLink();
        assertThat(page.title()).as("po kliknieciu jestesmy na innej stronie")
                .isNotEqualTo(originalTitle);

        page.goBack();
        assertThat(page.title()).as("przycisk wstecz przywraca poprzedni artykul")
                .isEqualTo(originalTitle);
    }
}
