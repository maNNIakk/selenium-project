package br.com.seleniumproject.core;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static br.com.seleniumproject.core.DriverFactory.getDriver;
import static br.com.seleniumproject.core.DriverFactory.killDriver;

public class BaseTest {

    @AfterEach
    public void killDriverParaCadaTeste() throws IOException {
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File("screenshot.jpg"));
        if(Propriedades.FECHAR_BROWSER_POR_TESTE) killDriver();

    }

    @AfterAll
    public static void killDriverParaCadaClasse(){
        if(!Propriedades.FECHAR_BROWSER_POR_TESTE) killDriver();
        System.out.println("Entrou no método");
    }
}
