package br.com.seleniumproject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class TesteFormularioCompleto {
    String nome = "Renato";
    String sobrenome = "Santos";
    String comidaFavorita = "frango";
    String escolaridade = "Mestrado";
    String[] esportes  = {"Natacao","Karate"};
    DSL dsl;


    WebDriver driver = new FirefoxDriver();

    @BeforeEach
    public void setup(){
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
        dsl = new DSL(driver);
    }
    @Test
    @Order(0)
    public void testeFormulario(){
        String xpath = String.format("//input[@value=\"%s\"]", comidaFavorita);
        dsl.escreve("elementosForm:nome",nome);
        dsl.escreve("elementosForm:Sobrenome",sobrenome);
        dsl.clicaRadio("elementosForm:sexo:0");
        driver.findElement(By.xpath(xpath)).click();
        dsl.selecionarCombo("elementosForm:escolaridade",escolaridade);
        dsl.selecionarCombo("elementosForm" +
                ":esportes",esportes[0]);

        dsl.clicarBotao("elementosForm:cadastrar");
        WebElement statusCadastro =
                driver.findElement(By.id("resultado"));
        Assertions.assertNotEquals("Status: Nao cadastrado",
                statusCadastro.getText());
        Assertions.assertTrue(statusCadastro.getText().startsWith("Cadastrado!"));
        Assertions.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith(nome));
        Assertions.assertEquals("Sobrenome: Santos",
                driver.findElement(By.id("descSobrenome")).getText());
    }

    @Test
    @Order(1)
    public void testeIFrame(){
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assertions.assertEquals("Frame OK!", msg);
        alert.accept();
        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
        Assertions.assertEquals("Frame OK!",
                driver.findElement(By.id("elementosForm:nome")).getAttribute(
                        "value"));
    }

    @Test
    @Order(2)
    public void testePopUpEasy(){
        driver.findElement(By.id("buttonPopUpEasy")).click();
        String windowHandler = driver.getWindowHandle();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Xablau");
        driver.close();
        driver.switchTo().window(windowHandler);
        System.out.println(windowHandler);
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Funfou po");
        Assertions.assertEquals("Campo de Treinamento", driver.getTitle());
    }

    @Test
    @Order(3)
    public void testePopUpHard(){
        driver.findElement(By.id("buttonPopUpHard")).click();
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        String [] teste = driver.getWindowHandles().toArray(new String[0]);
        System.out.println(teste[0]);
        driver.switchTo().window(teste[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Oxi que doideira");
        driver.close();
        driver.switchTo().window(teste[0]);
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Num é " +
                "que funcionou?");
        Assertions.assertEquals("Campo de Treinamento", driver.getTitle());

    }


    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
