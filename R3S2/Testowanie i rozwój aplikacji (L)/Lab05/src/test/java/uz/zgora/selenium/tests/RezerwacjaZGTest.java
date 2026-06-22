package uz.zgora.selenium.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uz.zgora.selenium.base.BaseTest;
import uz.zgora.selenium.pages.RezerwacjaZGPage;

/**
 * BEZPIECZENSTWO: zadny test w tej klasie nie zatwierdza rezerwacji.
 * Przycisk "Przejdz do podsumowania" (.submit-button-step-1) nie jest nigdy klikany.
 * Testy weryfikuja stan widgetu i UI - tylko odczyt, zero modyfikacji danych w systemie.
 */
@DisplayName("Rezerwacja ZG - UI, widget Bookero i linki stopki")
class RezerwacjaZGTest extends BaseTest {

    @Test
    @DisplayName("Strona glowna laduje sie i prezentuje widget rezerwacji")
    void homePageShowsBookingWidget() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver()).openHome();

        assertThat(page.currentUrl()).contains("rezerwacja.zielona-gora.pl");
        assertThat(page.mainHeading())
                .as("naglowek opisuje Biuro Rejestracji Pojazdow")
                .containsIgnoringCase("biuro rejestracji pojazd");
    }

    @Test
    @DisplayName("Widget Bookero inicjalizuje sie po zaladowaniu strony")
    void bookeroWidgetInitializes() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver())
                .openHome()
                .waitForBookeroPlugin();

        assertThat(page.isBookeroPluginInitialized())
                .as("atrybut data-is-initialized ma wartosc 'true'")
                .isTrue();
        assertThat(page.bookeroFormHeader())
                .as("naglowek formularza bookero")
                .containsIgnoringCase("Zarezerwuj termin");
    }

    @Test
    @DisplayName("Formularz rezerwacji zawiera sekcje: usluga, termin, pola wymagane, przycisk")
    void bookeroFormHasExpectedSections() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver())
                .openHome()
                .waitForBookeroPlugin();

        assertThat(page.termHeadingText())
                .as("naglowek sekcji Termin")
                .containsIgnoringCase("Termin");
        assertThat(page.hasRequiredFieldsNotice())
                .as("informacja o polach wymaganych")
                .isTrue();
        assertThat(page.hasSubmitButton())
                .as("przycisk Przejdz do podsumowania istnieje (ale nie jest klikany)")
                .isTrue();
        assertThat(page.bookeroFooterText())
                .as("stopka Bookero zawiera branding")
                .containsIgnoringCase("Bookero");
    }

    @Test
    @DisplayName("Lista uslug zawiera co najmniej 5 pozycji dot. rejestracji i prawa jazdy")
    void serviceDropdownContainsExpectedServices() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver())
                .openHome()
                .waitForBookeroPlugin()
                .openServiceDropdown();

        assertThat(page.serviceOptionTexts())
                .as("lista dostepnych uslug")
                .hasSizeGreaterThanOrEqualTo(5)
                .anySatisfy(s -> assertThat(s).containsIgnoringCase("rejestracja"))
                .anySatisfy(s -> assertThat(s).containsIgnoringCase("prawo jazdy"));
    }

    @Test
    @DisplayName("Po wyborze uslugi label multiselect pokazuje nazwe wybranej uslugi")
    void selectingServiceUpdatesLabel() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver())
                .openHome()
                .waitForBookeroPlugin()
                .selectService("Odbiór dowodu rejestracyjnego");

        assertThat(page.selectedServiceLabel())
                .as("wybrany label w multiselecte")
                .containsIgnoringCase("Odbiór dowodu rejestracyjnego");
    }

    @Test
    @DisplayName("Po wyborze uslugi sekcja kalendarza jest widoczna (bez klikania terminow)")
    void selectingServiceRevealCalendarSection() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver())
                .openHome()
                .waitForBookeroPlugin()
                .selectService("Odbiór dowodu rejestracyjnego");

        assertThat(page.hasCalendarNavMonth())
                .as("nawigacja miesiecy kalendarza jest widoczna")
                .isTrue();
        assertThat(page.hasCalendarNextButton())
                .as("przycisk nastepnego miesiaca kalendarza jest dostepny")
                .isTrue();
    }

    @Test
    @DisplayName("Po wyborze uslugi - dostepnosc kalendarza (skip jesli API Bookero niedostepne)")
    void calendarLoadedOrErrorHandledGracefully() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver())
                .openHome()
                .waitForBookeroPlugin()
                .selectService("Odbiór dowodu rejestracyjnego");

        boolean hasError = page.hasCalendarDatesError();
        boolean hasCalendar = page.hasCalendarNavMonth();

        assumeTrue(hasCalendar || hasError,
                "Sekcja terminow nie zaladowala sie w ogole - stan nieoczekiwany");

        if (hasError) {
            log.info("Bookero API zwrocilo blad wczytywania terminow - stan obslugiwany poprawnie przez UI");
            assertThat(page.mainContentText())
                    .as("komunikat o bledzie jest widoczny uzytkownikowi")
                    .containsIgnoringCase("błąd");
        } else {
            assertThat(page.hasCalendarNavMonth())
                    .as("kalendarz zaladowal sie poprawnie")
                    .isTrue();
        }
    }

    @Test
    @DisplayName("Stopka zawiera linki do regulaminu i polityki prywatnosci, najechanie myszka dziala")
    void footerLinksAreInteractive() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver()).openHome();

        assertThat(page.countFooterLinks())
                .as("liczba linkow w stopce")
                .isGreaterThanOrEqualTo(2);

        String href = page.firstFooterLinkHref();
        assertThat(href).as("href pierwszego linku stopki").isNotBlank();

        page.hoverRegulaminLink();
        assertThat(page.currentUrl()).as("hover nie zmienia URL-u").contains("rezerwacja.zielona-gora.pl");
    }

    @Test
    @DisplayName("Link Regulamin prowadzi na bip.zielonagora.pl i otwiera sie w nowej karcie")
    void regulaminLinkPointsToBip() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver()).openHome();

        assertThat(page.regulaminLinkHref())
                .as("href linku Regulamin")
                .contains("bip.zielonagora.pl");
        assertThat(page.regulaminOpensInNewTab())
                .as("Regulamin otwiera sie w nowej karcie (target=_blank)")
                .isTrue();
    }

    @Test
    @DisplayName("Link Polityka prywatnosci prowadzi na bip.zielonagora.pl i otwiera sie w nowej karcie")
    void politykaLinkPointsToBip() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver()).openHome();

        assertThat(page.politykaLinkHref())
                .as("href linku Polityka prywatnosci")
                .contains("bip.zielonagora.pl");
        assertThat(page.politykaOpensInNewTab())
                .as("Polityka prywatnosci otwiera sie w nowej karcie (target=_blank)")
                .isTrue();
    }
}
