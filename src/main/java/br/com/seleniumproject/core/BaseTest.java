package br.com.seleniumproject.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static br.com.seleniumproject.core.DriverFactory.killDriver;

public class BaseTest {

    @AfterEach
    public void killDriverParaCadaTeste() {
        if(Propriedades.FECHAR_BROWSER){
        killDriver();
        }
    }

    @AfterAll
    public void killDriverParaCadaClasse(){
        if(!Propriedades.FECHAR_BROWSER){
            killDriver();
        }
    }
}
