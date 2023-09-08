package br.com.seleniumproject.Tests;

import br.com.seleniumproject.DSL;
import br.com.seleniumproject.PO.CampoTreinamentoPO;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SecaoOrganizacaoCodigo {
    private WebDriver driver = new FirefoxDriver();
    private CampoTreinamentoPO campoTreinamento;
    private DSL dsl;

    //     <editor-fold defaultstate="collapsed" desc="Variáveis de cadastro">
    private String nome = "Renato";
    private String sobreNome = "Santos";
    private String[] sexo = {"M","F"};
    private String comidaFavorita = "carne";
    private String escolaridade = "Mestrado";
    private String observacaoAleatoria = "Sei lá o que escrever aqui bixo! Observação feita";
    // </editor-fold>

    boolean randomBoolean = Math.random() < 0.5;
    String sexoEscolhido = sexo[randomBoolean ? 1 : 0];


    @BeforeEach
    public void setup(){
        driver.manage().window().maximize();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
        dsl = new DSL(driver);
        campoTreinamento = new CampoTreinamentoPO(driver);
    }



    @Test @Order(0)
    public void utilizandoPageObjects(){
        campoTreinamento.setNome(nome);
        campoTreinamento.setSobrenome(sobreNome);
        campoTreinamento.setSexo(sexoEscolhido);
        campoTreinamento.setComidaFavorita(comidaFavorita);
        campoTreinamento.setEscolaridade(escolaridade);
        campoTreinamento.setTextoObservacao(observacaoAleatoria);
    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
