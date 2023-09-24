package br.com.seleniumproject.tests;

import br.com.seleniumproject.core.BaseTest;
import br.com.seleniumproject.core.DSL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static br.com.seleniumproject.core.DriverFactory.getDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteFormularioCompleto extends BaseTest {
    String nome = "Renato";
    String sobrenome = "Santos";
    String comidaFavorita = "frango";
    String escolaridade = "Mestrado";
    String[] esportes = {"Natacao", "Karate"};
    DSL dsl;


    @BeforeEach
    public void setup() {

        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
        dsl = new DSL();
    }

    @Test
    @Order(0)
    public void testeFormulario() {
        String xpath = String.format("//input[@value=\"%s\"]", comidaFavorita);
        dsl.escreve("elementosForm:nome", nome);
        dsl.escreve("elementosForm:Sobrenome", sobrenome);
        dsl.clicaRadio("elementosForm:sexo:0");
        getDriver().findElement(By.xpath(xpath)).click();
        dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
        dsl.selecionarCombo("elementosForm" +
                ":esportes", esportes[0]);

        dsl.clicarBotao("elementosForm:cadastrar");
        WebElement statusCadastro =
                getDriver().findElement(By.id("resultado"));
        Assertions.assertNotEquals("Status: Nao cadastrado",
                statusCadastro.getText());
        Assertions.assertTrue(statusCadastro.getText().startsWith("Cadastrado!"));
        Assertions.assertTrue(getDriver().findElement(By.id("descNome")).getText().endsWith(nome));
        Assertions.assertEquals("Sobrenome: Santos",
                getDriver().findElement(By.id("descSobrenome")).getText());
    }

    @Test
    @Order(1)
    public void testeIFrame() {
        getDriver().switchTo().frame("frame1");
        getDriver().findElement(By.id("frameButton")).click();
        Alert alert = getDriver().switchTo().alert();
        String msg = alert.getText();
        Assertions.assertEquals("Frame OK!", msg);
        alert.accept();
        getDriver().switchTo().defaultContent();
        getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg);
        Assertions.assertEquals("Frame OK!",
                getDriver().findElement(By.id("elementosForm:nome")).getAttribute(
                        "value"));
    }

    @Test
    @Order(2)
    public void testePopUpEasy() {
        getDriver().findElement(By.id("buttonPopUpEasy")).click();
        String windowHandler = getDriver().getWindowHandle();
        getDriver().switchTo().window("Popup");
        getDriver().findElement(By.tagName("textarea")).sendKeys("Xablau");
        getDriver().close();
        getDriver().switchTo().window(windowHandler);
        System.out.println(windowHandler);
        getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("Funfou po");
        Assertions.assertEquals("Campo de Treinamento", getDriver().getTitle());
    }

    @Test
    @Order(3)
    public void testePopUpHard() {
        getDriver().findElement(By.id("buttonPopUpHard")).click();
        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getWindowHandles());
        String[] teste = getDriver().getWindowHandles().toArray(new String[0]);
        System.out.println(teste[0]);
        getDriver().switchTo().window(teste[1]);
        getDriver().findElement(By.tagName("textarea")).sendKeys("Oxi que doideira");
        getDriver().close();
        getDriver().switchTo().window(teste[0]);
        getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("Num é " +
                "que funcionou?");
        Assertions.assertEquals("Campo de Treinamento", getDriver().getTitle());

    }
}
