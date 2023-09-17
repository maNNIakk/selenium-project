package br.com.seleniumproject.secao5;

import br.com.seleniumproject.DSL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestesComAjax {

    private WebDriver driver;
    private DSL dsl;

    @BeforeEach
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=fcdde");
        dsl = new DSL(driver);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Order(0)
    @Test
    public void testeSimplesComAjax(){
        dsl.escreve("j_idt286:name","Teste");
        dsl.clicarBotao("j_idt286:j_idt290");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBe(By.id("j_idt286:display"),"Teste"));
        Assertions.assertEquals("Teste", dsl.obterTexto("j_idt286:display"));
    }
}
