package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Profile {
    private final By headerConstructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By stellarBurgerLogotype = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    private final By menuItemProfile = By.xpath(".//a[text()='Профиль']");
    private final By menuItemExitButton = By.xpath(".//button[text()='Выход']");
    protected final WebDriver webDriver;

    public Profile(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitingForProfilePageLoading() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(menuItemProfile));
    }

    public void menuItemExitButtonClick() {
        webDriver.findElement(menuItemExitButton).click();
    }

    public void stellarBurgerLogotypeClick() {
        webDriver.findElement(stellarBurgerLogotype).click();
    }

    public void headerConstructorButtonClick() {
        webDriver.findElement(headerConstructorButton).click();
    }
}