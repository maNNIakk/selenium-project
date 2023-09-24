package br.com.seleniumproject.tests;

import br.com.seleniumproject.PO.CampoTreinamentoPO;
import br.com.seleniumproject.core.BaseTest;
import br.com.seleniumproject.core.DSL;
import org.junit.jupiter.api.*;

import static br.com.seleniumproject.core.DriverFactory.getDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SecaoOrganizacaoCodigo extends BaseTest {
    private CampoTreinamentoPO campoTreinamento;

    //     <editor-fold defaultstate="collapsed" desc="Variáveis de cadastro">
    private String nome = "Renato";
    private String sobreNome = "Santos";
    private String[] sexo = {"M", "F"};
    private String comidaFavorita = "carne";
    private String escolaridade = "Mestrado";
    private String observacaoAleatoria = "Sei lá o que escrever aqui bixo! Observação feita";
    // </editor-fold>

    boolean randomBoolean = Math.random() < 0.5;
    String sexoEscolhido = sexo[randomBoolean ? 1 : 0];
    DSL dsl = new DSL();

    @BeforeEach
    public void setup() {
        getDriver().manage().window().maximize();
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main" +
                "/resources/componentes.html");
        dsl = new DSL();
        campoTreinamento = new CampoTreinamentoPO();
    }


    @Test
    @Order(0)
    public void utilizandoPageObjects() {
        campoTreinamento.setNome(nome);
        campoTreinamento.setSobrenome(sobreNome);
        campoTreinamento.setSexo(sexoEscolhido);
        campoTreinamento.setComidaFavorita(comidaFavorita);
        campoTreinamento.setEscolaridade(escolaridade);
        campoTreinamento.setTextoObservacao(observacaoAleatoria);
    }
}
