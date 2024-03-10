package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Drivers {

    public static WebDriver setUp() {
        WebDriverManager.chromedriver().setup();
        if (System.getProperty("browser.type") != null && System.getProperty("browser.type").equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", Endpoints.YANDEX_DRIVER);
        }
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}