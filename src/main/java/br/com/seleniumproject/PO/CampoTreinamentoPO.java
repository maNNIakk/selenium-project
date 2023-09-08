package br.com.seleniumproject.PO;

import br.com.seleniumproject.DSL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPO {

    private DSL dsl;
    private WebDriver driver;

    public CampoTreinamentoPO(WebDriver driver) {
        dsl = new DSL(driver);
        this.driver = driver;

    }

    public void setNome(String nome){
        dsl.escreve("elementosForm:nome", nome);
    }
    public void setSobrenome(String sobreNome){
        dsl.escreve("elementosForm:sobrenome", sobreNome);
    }

    public void setSexo(String sexo) {
        // Construct the XPath selector based on the value attribute
        String radioButtonXPath = String.format("//input[@type='radio' and @value='%s']", sexo);

        // Find and click the radio button
        driver.findElement(By.xpath(radioButtonXPath)).click();
    }

    public void setComidaFavorita(String comidaFavorita){
        String radioButtonXPath = String.format("//input[@type='checkbox' and @value='%s']", comidaFavorita);

        // Find and click the radio button
        driver.findElement(By.xpath(radioButtonXPath)).click();
    }

    public void setEscolaridade(String escolaridade){
        dsl.selecionarCombo("elementosForm:escolaridade",escolaridade);
    }

    public void setTextoObservacao(String observacao){
        dsl.escreve("elementosForm:sugestoes",observacao);
    }


}
