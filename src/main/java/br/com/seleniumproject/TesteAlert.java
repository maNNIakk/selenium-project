package br.com.seleniumproject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TesteAlert {

    WebDriver driver = new FirefoxDriver();
    Alert alert = null;
    String texto = null;
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
        alert = driver.switchTo().alert();
        texto = alert.getText();
        Assertions.assertEquals("Alert Simples", texto);
        alert.accept();
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alerta " +
                "Simples");
        Assertions.assertEquals("Alerta Simples",driver.findElement(By.id(
                "elementosForm:nome")).getAttribute("value"));

    }
    @Test
    @Order(1)
    public void deveAceitarAlert(){
        driver.findElement(By.id("confirm")).click();
        alert = driver.switchTo().alert();
        alert.accept();
        texto = alert.getText();
        Assertions.assertEquals("Confirmado",texto);
    }
    @Test
    @Order(2)
    public void deveRecusarAlert(){
        driver.findElement(By.id("confirm")).click();
        alert = driver.switchTo().alert();
        alert.dismiss();
        texto = alert.getText();
        Assertions.assertEquals("Negado",texto);
    }

    @Test
    @Order(3)
    public void devePreencherPrompt(){
        driver.findElement(By.id("prompt")).click();
        alert = driver.switchTo().alert();
        texto = alert.getText();
        Assertions.assertEquals("Digite um numero",texto);
        String numero = "21";
        alert.sendKeys(numero);
        alert.accept();
        texto = alert.getText();
        if (texto.contains(numero)) {
            alert.accept();
            texto = alert.getText();
            Assertions.assertEquals(":D",texto);
        }
        else Assertions.fail();

    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
