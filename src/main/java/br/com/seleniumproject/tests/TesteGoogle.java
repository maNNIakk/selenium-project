package br.com.seleniumproject.tests;

import br.com.seleniumproject.core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static br.com.seleniumproject.core.DriverFactory.getDriver;
import static br.com.seleniumproject.core.DriverFactory.killDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteGoogle extends BaseTest {
    @Test
    @Order(0)
    public void teste() {
//
        getDriver().get("https://google.com");
        Assertions.assertEquals("Google", getDriver().getTitle());



    }
}
