package br.com.seleniumproject.secao5;

import br.com.seleniumproject.DSL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestesComSincronismo {

    private WebDriver driver;
    private DSL dsl;
    @BeforeEach
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @Order(0)
    @Test
    public void testandoXPathDinamicoCheckBox(){
        dsl.selecionarBotaoTabelaPeloNome("Usuario A","checkbox");
    }

    @Order(1)
    @Test
    public void testandoXPathDinamicoButton(){
        dsl.selecionarBotaoTabelaPeloNome("Francisco","button");
    }

    @Order(2)
    @Test
    public void testandoXPathDinamicoRadio(){
        dsl.selecionarBotaoTabelaPeloNome("Doutorado","radio");
    }

    @Order(3)
    @Test
    public void testandoXPathDinamicoText(){
        dsl.selecionarBotaoTabelaPeloNome("Usuario B","text","Funcionou carai!");
    }

    @Order(4)
    @Test
    public void testeSincronismoRuim() throws InterruptedException {
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escreve("novoCampo","Funfou?!");
    }

    @Order(5)
    @Test
    public void testeSincronismoBom(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.clicarBotao("buttonDelay");
        dsl.escreve("novoCampo","Funfou?!");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Order(6)
    @Test
    public void testeSincronismoExplicito(){
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
        dsl.escreve("novoCampo","Funfou?!");
    }

    @Order(7)
    @Test
    public void testeSincronismoComAjax(){

    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
