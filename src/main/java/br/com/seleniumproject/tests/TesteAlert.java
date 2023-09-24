package br.com.seleniumproject.tests;

import br.com.seleniumproject.core.BaseTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static br.com.seleniumproject.core.DriverFactory.getDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteAlert extends BaseTest {

    Alert alert = null;
    String texto = null;

    @BeforeEach
    public void setup() {
        
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
    }

    @Test
    @Order(0)
    public void deveInteragirComAlert() {
        getDriver().findElement(By.id("alert")).click();
        alert = getDriver().switchTo().alert();
        texto = alert.getText();
        Assertions.assertEquals("Alert Simples", texto);
        alert.accept();
        getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Alerta " +
                "Simples");
        Assertions.assertEquals("Alerta Simples", getDriver().findElement(By.id(
                "elementosForm:nome")).getAttribute("value"));

    }

    @Test
    @Order(1)
    public void deveAceitarAlert() {
        getDriver().findElement(By.id("confirm")).click();
        alert = getDriver().switchTo().alert();
        alert.accept();
        texto = alert.getText();
        Assertions.assertEquals("Confirmado", texto);
    }

    @Test
    @Order(2)
    public void deveRecusarAlert() {
        getDriver().findElement(By.id("confirm")).click();
        alert = getDriver().switchTo().alert();
        alert.dismiss();
        texto = alert.getText();
        Assertions.assertEquals("Negado", texto);
    }

    @Test
    @Order(3)
    public void devePreencherPrompt() {
        getDriver().findElement(By.id("prompt")).click();
        alert = getDriver().switchTo().alert();
        texto = alert.getText();
        Assertions.assertEquals("Digite um numero", texto);
        String numero = "21";
        alert.sendKeys(numero);
        alert.accept();
        texto = alert.getText();
        if (texto.contains(numero)) {
            alert.accept();
            texto = alert.getText();
            Assertions.assertEquals(":D", texto);
        } else Assertions.fail();

    }
}
