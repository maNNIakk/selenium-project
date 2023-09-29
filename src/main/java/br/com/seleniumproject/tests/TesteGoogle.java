package br.com.seleniumproject.tests;

import br.com.seleniumproject.core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static br.com.seleniumproject.core.DriverFactory.getDriver;

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
