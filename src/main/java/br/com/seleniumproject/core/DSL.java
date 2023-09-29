package br.com.seleniumproject.core;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static br.com.seleniumproject.core.DriverFactory.getDriver;

public class DSL {

    public void escreve(String inputId, String text) {
        getDriver().findElement(By.id(inputId)).sendKeys(text);
    }

    public String obterValorCampo(String inputId, String atributo) {
        return getDriver().findElement(By.id(
                inputId)).getAttribute(atributo);
    }

    public void clicaRadio(String inputId) {
        getDriver().findElement(By.id(inputId)).click();
    }

    public boolean isRadioMarcado(String id) {
        return getDriver().findElement(By.id(id)).isSelected();
    }

    public void selecionarCombo(String id, String valor) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public String obterValorCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public void clicarBotao(String id) {
        getDriver().findElement(By.id(id)).click();
    }

    public void clicarLink(String id) {
        getDriver().findElement(By.linkText(id)).click();
    }


    public String obterTexto(By by) {
        return getDriver().findElement(by).getText();
    }

    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    public Object executarJS(String cmd, Object... param) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        return js.executeScript(cmd, param);
    }

    public void selecionarBotaoTabelaPeloNome(String nome, String tipoInput, String... texto) {
        String xpath = String.format("//table[@id=\"elementosForm:tableUsuarios\"]/tbody/tr//td[1][contains(text(),'%s')]//..//td//input[@type=\"%s\"]", nome, tipoInput);
        WebElement element = getDriver().findElement(By.xpath(xpath));

        if (tipoInput == "button") {
            element.click();
            Alert alert = getDriver().switchTo().alert();
            Assertions.assertEquals(nome, alert.getText());
            alert.dismiss();
        } else if (tipoInput == "checkbox" || tipoInput == "radio") {
            element.click();
            Assertions.assertTrue(getDriver().findElement(By.xpath(xpath)).isSelected());
        } else {
            if (texto.length > 0) {
                for (String text : texto) {
                    element.sendKeys(text);
                    String inputValue = element.getAttribute("value");
                    System.out.println(element.getText());
                    Assertions.assertEquals(inputValue, text);
                }
            }
        }

        System.out.println(xpath);
    }
}
