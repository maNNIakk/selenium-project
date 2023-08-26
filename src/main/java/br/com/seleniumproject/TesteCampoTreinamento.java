package br.com.seleniumproject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assertions.assertTrue(driver.findElement(By.id("elementosForm" +
                ":comidaFavorita:2")).isSelected());
    }

    @Test
    @Order(4)
    public void deveValoresCombo(){
        WebElement element = driver.findElement(By.id("elementosForm" +
                ":escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assertions.assertEquals(8,options.size());

        boolean encontrou = false;
        for(WebElement option: options) {
            if(option.getText().equals("Mestrado")){
                encontrou = true;
                break;
            }
        }
        Assertions.assertTrue(encontrou);

    }

    @Test
    @Order(5)
    public void valoresMultiCombo(){
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assertions.assertEquals(3, allSelectedOptions.size());

    }

    @AfterEach
    public void tearDown(){
         driver.quit();
    }
}
