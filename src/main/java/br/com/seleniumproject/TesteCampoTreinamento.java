package br.com.seleniumproject;

import dev.failsafe.internal.util.Assert;
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
    DSL dsl;
    @BeforeEach
    public void setup(){

        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @Test
    @Order(0)
    public void testeTextField(){
        dsl.escreve("elementosForm:nome","Xablau");
        Assertions.assertEquals("Xablau",dsl.obterValorCampo("elementosForm:nome","value"));

    }

    @Test
    @Order(1)
    public void deveInteragirComTextArea(){
        dsl.escreve("elementosForm:sugestoes","Xablau no textArea");
        Assertions.assertEquals("Xablau no textArea",
                dsl.obterValorCampo("elementosForm:sugestoes","value"));
    }

    @Test
    @Order(2)
    public void deveInteragirComRadio(){
        dsl.clicaRadio("elementosForm:sexo:0");
        Assertions.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    @Order(3)
    public void deveInteragirComCheckBox(){
        dsl.clicaRadio("elementosForm:comidaFavorita:2");
        Assertions.assertTrue(dsl.isRadioMarcado("elementosForm" +
                ":comidaFavorita:2"));
    }

    @Test
    @Order(4)
    public void deveValoresCombo(){
        dsl.selecionarCombo("elementosForm" +
                ":escolaridade","Mestrado");
        Assertions.assertEquals("Mestrado", dsl.obterValorCombo("elementosForm" +
                ":escolaridade"));

    }

    @Test
    @Order(5)
    public void valoresMultiCombo(){
        dsl.selecionarCombo("elementosForm:esportes","Natacao");
        dsl.selecionarCombo("elementosForm:esportes","Corrida");
        dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assertions.assertEquals(3, allSelectedOptions.size());

    }

    @Test
    @Order(6)
    public void deveInteragirComBotoes(){
        dsl.clicarBotao("buttonSimple");
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assertions.assertEquals("Obrigado!",botao.getAttribute("value"));

    }
    @Test
    @Order(7)
    public void deveInteragirComLink(){
        dsl.clicarLink("Voltar");
        Assertions.assertEquals("Voltou!",
                dsl.obterTexto("resultado"));
        Assertions.assertTrue(dsl.obterTexto("resultado").contains("Voltou!"));
        Assertions.assertEquals("Cuidado onde clica, muitas armadilhas...",
        dsl.obterTexto(By.className("facilAchar")));
    }

    @AfterEach
    public void tearDown(){
         driver.quit();
    }
}
