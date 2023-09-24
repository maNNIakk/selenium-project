package br.com.seleniumproject.tests;

import br.com.seleniumproject.PO.CampoTreinamentoPO;
import br.com.seleniumproject.core.BaseTest;
import br.com.seleniumproject.core.DSL;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

import static br.com.seleniumproject.core.DriverFactory.getDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterizedTests extends BaseTest {
    private CampoTreinamentoPO campoTreinamento;
    private DSL dsl;

    @BeforeEach
    public void setup() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
        dsl = new DSL();
        campoTreinamento = new CampoTreinamentoPO();
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("John", "Doe", "M", "pizza", "Superior", "This is a test observation."),
                Arguments.of("Alice", "Smith", "F", "carne", "Mestrado", "Another observation."),
                Arguments.of("Bob", "Johnson", "M", "vegetariano", "Doutorado", "Observation for Bob.")
        );
    }


    @Order(0)
    @ParameterizedTest
    @MethodSource("testData")
    public void utilizandoPageObjects(String nome, String sobreNome, String sexoEscolhido, String comidaFavorita, String escolaridade, String observacaoAleatoria) {
        campoTreinamento.setNome(nome);
        campoTreinamento.setSobrenome(sobreNome);
        campoTreinamento.setSexo(sexoEscolhido);
        campoTreinamento.setComidaFavorita(comidaFavorita);
        campoTreinamento.setEscolaridade(escolaridade);
        campoTreinamento.setTextoObservacao(observacaoAleatoria);
        dsl.clicarBotao("elementosForm:cadastrar");
        String resultado = getDriver().findElement(By.id("resultado")).getText();
        Assertions.assertTrue(resultado.startsWith("Cadastrado!"));
    }

    @Order(1)
    @Test
    public void testJavascript() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        js.executeScript("alert('Testando JS Via Selenium')");
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via Selenium'");
        js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
        WebElement element = getDriver().findElement(By.id("elementosForm:nome"));

        js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
    }


    @Order(2)
    @Test
    public void maisTesteComIFrame() {
        WebElement frame = getDriver().findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        getDriver().switchTo().frame("frame2");
        dsl.clicarBotao("frameButton");
        Alert alert = getDriver().switchTo().alert();
        String msg = alert.getText();
        alert.dismiss();
        Assertions.assertEquals("Frame OK!", msg);

    }

}
