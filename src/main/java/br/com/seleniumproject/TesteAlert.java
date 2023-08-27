package br.com.seleniumproject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteAlert {

    WebDriver driver = new FirefoxDriver();

    @BeforeEach
    public void setup(){
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
    }

    @Test
    @Order(0)
    public void deveInteragirComAlert(){
        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        Assertions.assertEquals("Alert Simples", texto);
        alert.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alerta " +
                "Simples");
        Assertions.assertEquals("Alerta Simples",driver.findElement(By.id(
                "elementosForm:nome")).getAttribute("value"));

    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
