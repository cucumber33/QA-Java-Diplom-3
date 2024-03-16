package page.object;

import user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    private final By emailInputBox = By.xpath(".//label[text()='Email']/..//input");
    private final By passwordInputBox = By.xpath(".//label[text()='Пароль']/..//input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By loginFormTitle = By.xpath(".//*[text() = 'Вход']");
    protected final WebDriver webDriver;

    public Login(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitingForLoginFormLoading() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loginFormTitle));
    }

    public void insertPassword(String password) {
        webDriver.findElement(passwordInputBox).sendKeys(password);
    }

    public void insertEmail(String email) {
        webDriver.findElement(emailInputBox).sendKeys(email);
    }

    public void loginButtonClick() {
        webDriver.findElement(loginButton).click();
    }

    public void insertUserDataAndClickLoginButton(User user) {
        insertEmail(user.getEmail());
        insertPassword(user.getPassword());
        loginButtonClick();
    }
}
