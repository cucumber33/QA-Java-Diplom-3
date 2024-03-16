import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.example.Drivers;

import java.time.Duration;


public class PrepareTest extends ExternalResource {
    private WebDriver webDriver;
    // чтобы запустить тесты в MozillaFirefox, нужно поменять "chrome" на "firefox"
    String property = System.getProperty("browser", "chrome");

    @Override
    protected void before() {
        webDriver = Drivers.getDriver(property.toLowerCase());
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    @Override
    protected void after() {
        webDriver.quit();
    }
}