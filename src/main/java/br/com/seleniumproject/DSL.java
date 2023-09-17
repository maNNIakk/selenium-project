package br.com.seleniumproject;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

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

    public void clicaRadio(String inputId){
        driver.findElement(By.id(inputId)).click();
    }

    public boolean isRadioMarcado(String id){
        return driver.findElement(By.id(id)).isSelected();
    }

    public void selecionarCombo(String id, String valor){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String obterValorCombo(String id){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public void clicarBotao(String id){
        driver.findElement(By.id(id)).click();
    }

    public void clicarLink(String id){
        driver.findElement(By.linkText(id)).click();
    }


    public String obterTexto(By by){
        return driver.findElement(by).getText();
    }
    public String obterTexto(String id){
        return obterTexto(By.id(id));
    }

    public Object executarJS(String cmd, Object... param){
        JavascriptExecutor js = (JavascriptExecutor) driver;

        return js.executeScript(cmd,param);
    }

    public void selecionarBotaoTabelaPeloNome(String nome, String tipoInput, String... texto){
        String xpath = String.format("//table[@id=\"elementosForm:tableUsuarios\"]/tbody/tr//td[1][contains(text(),'%s')]//..//td//input[@type=\"%s\"]",nome,tipoInput);
        WebElement element = driver.findElement(By.xpath(xpath));

        if (tipoInput == "button"){
        element.click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals(nome,alert.getText());
        } else if (tipoInput == "checkbox" || tipoInput == "radio" ){
            element.click();
            Assertions.assertTrue(driver.findElement(By.xpath(xpath)).isSelected());
        }
        else {
            if(texto.length > 0){
                for (String text : texto){
                    element.sendKeys(text);
                    String inputValue = element.getAttribute("value");
                    System.out.println(element.getText());
                    Assertions.assertEquals(inputValue,text);
                }
            }
        }
        System.out.println(xpath);
    }
}
