package br.com.seleniumproject.Tests;

import br.com.seleniumproject.DSL;
import br.com.seleniumproject.PO.CampoTreinamentoPO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterizedTests{
    private WebDriver driver = new FirefoxDriver();
    private CampoTreinamentoPO campoTreinamento;
    private DSL dsl;

    @BeforeEach
    public void setup(){
        driver.manage().window().setSize(new Dimension(1200,765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
        dsl = new DSL(driver);
        campoTreinamento = new CampoTreinamentoPO(driver);
    }

    static Stream<Arguments> testData(){
        return Stream.of(
                Arguments.of("John", "Doe", "M", "pizza", "Superior", "This is a test observation."),
                Arguments.of("Alice", "Smith", "F", "carne", "Mestrado", "Another observation."),
                Arguments.of("Bob", "Johnson", "M", "vegetariano", "Doutorado", "Observation for Bob.")
        );
    }


    @Order(0)
    @Test
    @ParameterizedTest
    @MethodSource("testData")
    public void utilizandoPageObjects(String nome, String sobreNome, String sexoEscolhido, String comidaFavorita, String escolaridade, String observacaoAleatoria){
        campoTreinamento.setNome(nome);
        campoTreinamento.setSobrenome(sobreNome);
        campoTreinamento.setSexo(sexoEscolhido);
        campoTreinamento.setComidaFavorita(comidaFavorita);
        campoTreinamento.setEscolaridade(escolaridade);
        campoTreinamento.setTextoObservacao(observacaoAleatoria);
        dsl.clicarBotao("elementosForm:cadastrar");
        String resultado =  driver.findElement(By.id("resultado")).getText();
        Assertions.assertTrue(resultado.startsWith("Cadastrado!"));
    }

    @Order(1)
    @Test
    public void testJavascript(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("alert('Testando JS Via Selenium')");
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via Selenium'");
        js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
        WebElement element = driver.findElement(By.id("elementosForm:nome"));

        js.executeScript("arguments[0].style.border = arguments[1]", element , "solid 4px red");
    }


    @Order(2)
    @Test
    public void maisTesteComIFrame(){
     WebElement frame = driver.findElement(By.id("frame2"));
     dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
     driver.switchTo().frame("frame2");
     dsl.clicarBotao("frameButton");
     Alert alert = driver.switchTo().alert();
     String msg = alert.getText();
     alert.dismiss();
     Assertions.assertEquals("Frame OK!", msg);

    }
//    @AfterEach
//    public void tearDown(){
//        driver.quit();
//    }
}
