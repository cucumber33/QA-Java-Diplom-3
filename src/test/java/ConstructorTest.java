import org.example.Endpoints;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest{
    @Rule
    public PrepareTest prepareTest = new PrepareTest();
    public MainPage mainPage;
    @Before
    public void setUp() {
        WebDriver webDriver = prepareTest.getWebDriver();
        mainPage = new MainPage(webDriver);
        webDriver.get(Endpoints.BASE_URL);
    }
    @Test
    public void checkSaucesPass() {
        mainPage.clickOnSaucesMenu();
        assertTrue("Выбрана вкладка соусов", mainPage.saucesMenuIsSelected());
    }

    @Test
    public void checkFillingsPass() {
        mainPage.clickOnFillingsMenu();
        assertTrue("Выбрана вкладка начинок", mainPage.fillingsMenuIsSelected());
    }

    @Test
    public void checkBunsPass() {
        mainPage.clickOnFillingsMenu();
        mainPage.clickOnBunsMenu();
        assertTrue("Выбрана вкладка соусов", mainPage.bunsMenuIsSelected());
    }
}