package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecovery {
    private final By signInLink = By.xpath(".//a[text()='Войти']");
    protected final WebDriver webDriver;

    public PasswordRecovery(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickSignInLink() {
        webDriver.findElement(signInLink).click();
    }
}
