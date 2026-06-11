package uz.zgora.selenium.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uz.zgora.selenium.base.BaseTest;
import uz.zgora.selenium.pages.RezerwacjaZGPage;

@DisplayName("Rezerwacja ZG - menu i przelaczanie podstron")
class RezerwacjaZGTest extends BaseTest {

    @Test
    @DisplayName("Strona glowna laduje sie i prezentuje widget rezerwacji")
    void homePageShowsBookingWidget() {
        RezerwacjaZGPage page = new RezerwacjaZGPage(getDriver()).openHome();

        assertThat(page.currentUrl()).contains("rezerwacja.zielona-gora.pl");
        assertThat(page.mainHeading())
                .as("naglowek opisuje Biuro Rejestracji Pojazdow")
                .containsIgnoringCase("biuro rejestracji pojazd");
        assertThat(page.hasBookingWidget())
                .as("strona zawiera widget rezerwacji bookero")
                .isTrue();
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
}
