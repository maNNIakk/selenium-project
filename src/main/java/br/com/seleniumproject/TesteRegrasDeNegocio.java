package br.com.seleniumproject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteRegrasDeNegocio {

    WebDriver driver = new FirefoxDriver();
    Alert alert = null;


    @BeforeEach
    public void setup(){
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
    }

    @Test
    @Order(0)
    public void validarNome(){
        WebElement inputNome = driver.findElement(By.id("elementosForm:nome"));
        String assertInputNome = inputNome.getText();
        Assertions.assertTrue(assertInputNome.isEmpty());
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        Assertions.assertEquals(textAlert,"Nome eh obrigatorio");

    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }

}
