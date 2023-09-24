package br.com.seleniumproject.tests;

import br.com.seleniumproject.core.BaseTest;
import br.com.seleniumproject.core.DSL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static br.com.seleniumproject.core.DriverFactory.getDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestesComSincronismo extends BaseTest {

    private DSL dsl;

    @BeforeEach
    public void setup() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
        dsl = new DSL();
    }

    @Order(0)
    @Test
    public void testandoXPathDinamicoCheckBox() {
        dsl.selecionarBotaoTabelaPeloNome("Usuario A", "checkbox");
    }

    @Order(1)
    @Test
    public void testandoXPathDinamicoButton() {
        dsl.selecionarBotaoTabelaPeloNome("Francisco", "button");
    }

    @Order(2)
    @Test
    public void testandoXPathDinamicoRadio() {
        dsl.selecionarBotaoTabelaPeloNome("Doutorado", "radio");
    }

    @Order(3)
    @Test
    public void testandoXPathDinamicoText() {
        dsl.selecionarBotaoTabelaPeloNome("Usuario B", "text", "Funcionou carai!");
    }

    @Order(4)
    @Test
    public void testeSincronismoRuim() throws InterruptedException {
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escreve("novoCampo", "Funfou?!");
    }

    @Order(5)
    @Test
    public void testeSincronismoBom() {
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.clicarBotao("buttonDelay");
        dsl.escreve("novoCampo", "Funfou?!");
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Order(6)
    @Test
    public void testeSincronismoExplicito() {
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escreve("novoCampo", "Funfou?!");
    }

}
