package page.object;

import io.qameta.allure.Step;
import org.example.Endpoints;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final By orderPlaceButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By profileEnterButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By accountEnterButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By burgerAssembleTitle = By.xpath(".//*[text()='Соберите бургер']");
    private final By menuBuns = By.xpath(".//span[text()='Булки']/..");
    private final By menuSauces = By.xpath(".//span[text()='Соусы']/..");
    private final By menuFillings = By.xpath(".//span[text()='Начинки']/..");

    protected final WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitingForMainPageLoading() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(burgerAssembleTitle));
    }

    public boolean orderPlaceButtonIsDisplayed() {
        return webDriver.findElement(orderPlaceButton).isDisplayed();
    }

    public void clickOnProfileEnterButton() {
        webDriver.findElement(profileEnterButton).click();
    }

    public void clickOnAccountEnterButton() {
        webDriver.findElement(accountEnterButton).click();
    }

    public void clickOnBunsMenu() {
        webDriver.findElement(menuBuns).click();
    }

    public void clickOnSaucesMenu() {
        webDriver.findElement(menuSauces).click();
    }

    public void clickOnFillingsMenu() {
        webDriver.findElement(menuFillings).click();
    }

    public boolean bunsMenuIsSelected() {
        return webDriver.findElement(menuBuns).getAttribute("class").contains("current");
    }

    public boolean saucesMenuIsSelected() {
        return webDriver.findElement(menuSauces).getAttribute("class").contains("current");
    }

    public boolean fillingsMenuIsSelected() {
        return webDriver.findElement(menuFillings).getAttribute("class").contains("current");
    }
}