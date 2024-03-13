import org.example.Endpoints;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.object.MainPage;

public class PrepareTest {
    protected WebDriver driver;
    protected MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(Endpoints.BASE_URL);
        mainPage = new MainPage(driver, 10);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}