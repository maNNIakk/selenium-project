package br.com.seleniumproject.tests;

import br.com.seleniumproject.core.BaseTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static br.com.seleniumproject.core.DriverFactory.getDriver;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteRegrasDeNegocio extends BaseTest {

   
    Alert alert = null;

    WebElement inputSobrenome, inputNome, inputSexoMasc, btnCadastrar, checkCarne, checkVegan, menuEsportes;
    String assertInput,
            alertText;
    Select opcoesEsportes;

    @BeforeAll
    public void setup() {


        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");

        btnCadastrar = getDriver().findElement(By.id("elementosForm:cadastrar"));
    }


    @Test
    @Order(0)
    public void validarNome() {

        inputNome = getDriver().findElement(By.id("elementosForm:nome"));
        assertInput = inputNome.getAttribute("value");
        Assertions.assertTrue(assertInput.isEmpty());
        btnCadastrar.click();
        alert = getDriver().switchTo().alert();
        alertText = alert.getText();
        Assertions.assertEquals(alertText, "Nome eh obrigatorio");
        alert.dismiss();

    }

    @Test
    @Order(1)
    public void validarSobrenome() {

        inputNome.sendKeys("Renatão");
        assertInput = inputNome.getAttribute("value");
        Assertions.assertEquals(assertInput, "Renatão");

        inputSobrenome = getDriver().findElement(By.id("elementosForm:sobrenome"));
        Assertions.assertTrue(inputSobrenome.getAttribute("value").isEmpty());
        btnCadastrar.click();
        alert = getDriver().switchTo().alert();
        alertText = alert.getText();
        Assertions.assertEquals(alertText, "Sobrenome eh obrigatorio");
        alert.dismiss();

    }

    @Test
    @Order(2)
    public void validarSexo() {
        inputSobrenome.sendKeys("Dos Santão");
        assertInput = inputSobrenome.getAttribute("value");
        Assertions.assertEquals(assertInput, "Dos Santão");

        inputSexoMasc = getDriver().findElement(By.id("elementosForm:sexo:1"));
        Assertions.assertFalse(inputSexoMasc.isSelected());
        btnCadastrar.click();

        alert = getDriver().switchTo().alert();
        alertText = alert.getText();
        Assertions.assertEquals(alertText, "Sexo eh obrigatorio");
        alert.dismiss();

    }

    @Test
    @Order(3)
    public void validarVeganoOuCarnivoro() {
        inputSexoMasc.click();
        Assertions.assertTrue(inputSexoMasc.isSelected());
        checkCarne = getDriver().findElement(By.cssSelector("[value=\"carne\"]"));
        checkVegan = getDriver().findElement(By.cssSelector("[value=\"vegetariano\"]"));

        checkCarne.click();
        checkVegan.click();

        if (checkCarne.isSelected() && checkVegan.isSelected()) {
            btnCadastrar.click();
            alert = getDriver().switchTo().alert();
            alertText = alert.getText();
            Assertions.assertEquals(alertText, "Tem certeza que voce eh vegetariano?");
            alert.dismiss();
            checkVegan.click();
            Assertions.assertFalse(checkVegan.isSelected());
        } else {
            throw new InvalidArgumentException("Oxi Deu Errado");
        }
    }

    @Test
    @Order(4)
    public void validarMenuEsportes() {
        menuEsportes = getDriver().findElement(By.id("elementosForm:esportes"));
        opcoesEsportes = new Select(menuEsportes);
        opcoesEsportes.selectByValue("futebol");
        opcoesEsportes.selectByValue("nada");

        btnCadastrar.click();
        alert = getDriver().switchTo().alert();
        alertText = alert.getText();
        Assertions.assertEquals(alertText, "Voce faz esporte ou nao?");
        alert.dismiss();

        opcoesEsportes.deselectByValue("nada");
        Assertions.assertEquals(opcoesEsportes.getFirstSelectedOption().getAttribute("value"), "futebol");

    }

    @Test
    @Order(5)
    public void validarCadastroPreenchido() {
        btnCadastrar.click();
        WebElement statusCadastro =
                getDriver().findElement(By.id("resultado"));
        Assertions.assertNotEquals("Status: Nao cadastrado",
                statusCadastro.getText());
        Assertions.assertTrue(statusCadastro.getText().startsWith("Cadastrado!"));


    }
}
