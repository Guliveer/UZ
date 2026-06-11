package uz.zgora.selenium.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import uz.zgora.selenium.base.BaseTest;
import uz.zgora.selenium.pages.TheInternetPages;

@DisplayName("The-Internet - formularz logowania, checkboxy i dropdown")
class TheInternetFormTest extends BaseTest {

    @Nested
    @DisplayName("Formularz logowania")
    class LoginForm extends BaseTest {

        @Test
        @DisplayName("Poprawne dane wyswietlaja komunikat sukcesu")
        void successfulLogin() {
            var login = new TheInternetPages.Login(getDriver())
                    .openLoginPage()
                    .submitCredentials("tomsmith", "SuperSecretPassword!");

            assertThat(login.isSuccess()).isTrue();
            assertThat(login.flashMessage()).containsIgnoringCase("logged into");
        }

        @Test
        @DisplayName("Bledne haslo wyswietla komunikat bledu")
        void failedLogin() {
            var login = new TheInternetPages.Login(getDriver())
                    .openLoginPage()
                    .submitCredentials("tomsmith", "wrongPassword!");

            assertThat(login.isSuccess()).isFalse();
            assertThat(login.flashMessage()).containsIgnoringCase("invalid");
        }
    }

    @Nested
    @DisplayName("Zaznaczanie checkboxow")
    class Checkboxes extends BaseTest {

        @Test
        @DisplayName("Toggle checkboxow zmienia ich stan dwukierunkowo")
        void canToggleCheckboxes() {
            var page = new TheInternetPages.Checkboxes(getDriver()).openPage();
            assertThat(page.count()).isGreaterThanOrEqualTo(2);

            page.setChecked(0, true).setChecked(1, false);
            assertThat(page.isChecked(0)).as("checkbox #1 zaznaczony").isTrue();
            assertThat(page.isChecked(1)).as("checkbox #2 odznaczony").isFalse();

            page.setChecked(0, false).setChecked(1, true);
            assertThat(page.isChecked(0)).isFalse();
            assertThat(page.isChecked(1)).isTrue();
        }
    }

    @Nested
    @DisplayName("Lista rozwijana")
    class Dropdown extends BaseTest {

        @Test
        @DisplayName("Wybor 'Option 2' ustawia value='2'")
        void canSelectFromDropdown() {
            var page = new TheInternetPages.Dropdown(getDriver())
                    .openPage()
                    .select("Option 2");
            assertThat(page.selectedValue()).isEqualTo("2");
        }
    }
}
