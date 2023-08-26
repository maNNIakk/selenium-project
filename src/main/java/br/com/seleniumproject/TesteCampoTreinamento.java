package br.com.seleniumproject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {
    WebDriver driver = new FirefoxDriver();
    @BeforeEach
    public void setup(){

        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");

    }

    @Test
    @Order(0)
    public void testeTextField(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Xablau");
        Assertions.assertEquals("Xablau",driver.findElement(By.id(
                "elementosForm:nome")).getAttribute("value"));

    }

    @Test
    @Order(1)
    public void deveInteragirComTextArea(){
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Xablau" +
                " no textArea");
        Assertions.assertEquals("Xablau no textArea",
                driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }

    @Test
    @Order(2)
    public void deveInteragirComRadio(){
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assertions.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    @Order(3)
    public void deveInteragirComCheckBox(){

    }

    @AfterEach
    public void tearDown(){
//        driver.quit();
    }
}
