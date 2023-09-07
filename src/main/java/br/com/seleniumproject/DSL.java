package br.com.seleniumproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DSL {
    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escreve(String inputId, String text){
        driver.findElement(By.id(inputId)).sendKeys(text);
    }

    public String obterValorCampo(String inputId, String atributo){
        return driver.findElement(By.id(
                inputId)).getAttribute(atributo);
    }
}
