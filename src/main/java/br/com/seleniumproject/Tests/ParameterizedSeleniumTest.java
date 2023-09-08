package br.com.seleniumproject.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.stream.Stream;

public class ParameterizedSeleniumTest {


    private WebDriver driver;

    @BeforeEach
    @ParameterizedTest
    @MethodSource("provideSearchData")
    void setUp(String browser, String searchQuery) {
        // Get the "browser" system property or default to "chrome"


        // Initialize the WebDriver based on the "browser" system property
        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions ops = new ChromeOptions();
            ops.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(ops);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchQuery);
        searchBox.submit();

        // Here, you can add verification logic based on the search results
        // For simplicity, we'll just print the page title
        System.out.println("Page title for query '" + searchQuery + "' in " + browser + ": " + driver.getTitle());
    }

    @Test
    void testSearch() {
        System.out.println(driver.getTitle());
    }

    static Stream<Arguments> provideSearchData() {
        // Define test arguments as a Stream of Arguments, including the browser
        return Stream.of(
                Arguments.of("chrome", "Selenium WebDriver"),
                Arguments.of("chrome", "JUnit 5"),
                Arguments.of("firefox", "Parameterized Tests in JUnit"),
                Arguments.of("firefox", "Java Programming")
        );
    }

    @AfterEach
    void tearDown() {
        // Close the WebDriver after each test
            driver.quit();
    }
}


