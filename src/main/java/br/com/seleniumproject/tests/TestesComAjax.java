package br.com.seleniumproject.tests;

import br.com.seleniumproject.core.BaseTest;
import br.com.seleniumproject.core.DSL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static br.com.seleniumproject.core.DriverFactory.getDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestesComAjax extends BaseTest {


    private DSL dsl;

    @BeforeEach
    public void setup() {

        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=fcdde");
        dsl = new DSL();
    }

    @Order(0)
    @Test
    public void testeSimplesComAjax() {
        dsl.escreve("j_idt286:name", "Teste");
        dsl.clicarBotao("j_idt286:j_idt290");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBe(By.id("j_idt286:display"), "Teste"));
        Assertions.assertEquals("Teste", dsl.obterTexto("j_idt286:display"));
    }
}
