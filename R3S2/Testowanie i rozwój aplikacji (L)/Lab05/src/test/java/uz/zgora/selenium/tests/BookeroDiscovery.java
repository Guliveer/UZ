package uz.zgora.selenium.tests;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import uz.zgora.selenium.base.BaseTest;
import uz.zgora.selenium.config.TestConfig;

@DisplayName("Bookero discovery - tymczasowy test do zbadania DOM widgetu")
class BookeroDiscovery extends BaseTest {

    @Test
    @DisplayName("Dump DOM widgetu Bookero po zaladowaniu JS")
    void dumpBookeroDom() throws Exception {
        getDriver().get(TestConfig.get().url("app.rezerwacja.url"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        Thread.sleep(8000);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        js.executeScript("var b = document.querySelector('.info button, .info a, .info .accept, .info-bar button'); if(b) b.click(); var i = document.querySelector('.info'); if(i) i.style.display='none';");
        Thread.sleep(500);
        js.executeScript("document.querySelector('.multiselect').click();");
        Thread.sleep(800);
        js.executeScript(
                "var opts = Array.from(document.querySelectorAll('.multiselect__option > span'));"
                        + "var match = opts.find(s => s.textContent.includes('Odbiór dowodu'));"
                        + "if (match) match.parentElement.click();");
        Thread.sleep(5000);

        String html = (String) js.executeScript(
                "var c = document.getElementById('bookero-plugin'); return c ? c.outerHTML : '';");
        Path out = Path.of("target/bookero-dom-after-service.html");
        Files.createDirectories(out.getParent());
        Files.writeString(out, html);
        log.info("=== ZAPISANO: {} ({} chars)", out.toAbsolutePath(), html.length());
    }
}
