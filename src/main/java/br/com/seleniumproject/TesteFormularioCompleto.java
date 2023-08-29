package br.com.seleniumproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class TesteFormularioCompleto {
    String nome = "Renato";
    String sobrenome = "Santos";
    String comidaFavorita = "frango";
    String[] esportes  = {"natacao","Karate"};


    WebDriver driver = new FirefoxDriver();

    @BeforeEach
    public void setup(){
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
    }
    @Test
    public void testeFormulario(){
        String xpath = String.format("//input[@value=\"%s\"]", comidaFavorita);
        driver.findElement(By.id("elementosForm:nome")).sendKeys(nome);
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys(sobrenome);
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        System.out.println(xpath);
        driver.findElement(By.xpath(xpath)).click();
        WebElement element = driver.findElement(By.id("elementosForm" +
                ":esportes"));
        Select combo = new Select(element);
        List<WebElement> opcoes = combo.getOptions();
        for (WebElement opcao: opcoes ){
            for (int i = 0; i < esportes.length; i++) {
                if(opcao.getAttribute("value").equals(esportes[i])){
                    opcao.click();
                }
            }
        }

    }

//    @AfterEach
//    public void tearDown(){
//        driver.quit();
//    }
}
