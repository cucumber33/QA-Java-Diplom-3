package page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecovery {
    private final By signInLink = By.xpath(".//a[text()='Войти']");
    private WebDriver driver;

    public PasswordRecovery(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }
}
