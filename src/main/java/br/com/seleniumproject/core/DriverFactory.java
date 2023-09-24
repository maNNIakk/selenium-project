package br.com.seleniumproject.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static WebDriver driver;

    public DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
