package br.com.seleniumproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TesteGoogle {
    @Test
    @Order(0)
    public void teste() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        WebDriver driver = new ChromeDriver(options);
        WebDriver driver = new FirefoxDriver();
//        driver.manage().window().setSize(new Dimension(1200,765));
        driver.manage().window().maximize();
        driver.get("https://google.com");
        Assertions.assertEquals("Google",driver.getTitle());
        driver.quit();


    }
}
