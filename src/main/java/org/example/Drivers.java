package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import page.object.LogOut;

public class Drivers {

    // Выбор драйвера для конкретного браузера по его названию
    @Step("Выбор драйвера в зависимости от входных параметров")
    public static WebDriver getDriver(String browserName){
        switch (browserName){
            case "chrome":
                // Добавление свойств браузера Google Chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                WebDriver driver = new ChromeDriver(options);
                return driver;
            case "firefox":
                // Добавление свойств браузера Firefox
                FirefoxOptions optionsF = new FirefoxOptions();
                return new FirefoxDriver(optionsF);
            default:
                throw new RuntimeException("Введено некорректное название браузера");
        }
    }
}