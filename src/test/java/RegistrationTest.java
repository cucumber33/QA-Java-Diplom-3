import org.example.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.chrome.ChromeDriver;
import user.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.*;

public class RegistrationTest {

    public WebDriver driver;
    public LogOut logOut;
    public Login login;
    public static String accessToken;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        RestAssured.baseURI = Endpoints.BASE_URL;
        logOut = new LogOut(driver);
        driver.get(Endpoints.BASE_URL + Endpoints.REGISTER);
        logOut.waitingForSignUpPageLoading();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public static void afterClass() {
        UserSteps.deleteUser(accessToken);
    }


    @Test
    public void signUpTest() {
        User user = RandomUser.createNewRandomUser();
        login = new Login(driver);
        logOut.insertUserLogOutData(user);
        logOut.clickSignUpButton();
        login.waitingForLoginFormLoading();
        Assert.assertEquals("Перешли на страницу логина", Endpoints.BASE_URL + Endpoints.LOGIN, driver.getCurrentUrl());
        Response response = UserSteps.authUser(user);
        Assert.assertEquals("Удалось залогиниться с данными созданного пользователя", 200, response.statusCode());
        accessToken = response.path("accessToken");
    }

    @Test
    public void signUpWithShortPasswordTest() {
        User user = RandomUser.createNewRandomUser();
        user.setPassword("12345");
        logOut.insertUserLogOutData(user);
        logOut.clickSignUpButton();
        Assert.assertTrue("Отображается ошибка о некорректном пароле", logOut.checkSignUpWrongPasswordError());
        Assert.assertEquals("Остались на странице логина", Endpoints.BASE_URL + Endpoints.REGISTER, driver.getCurrentUrl());
        Response response = UserSteps.authUser(user);
        Assert.assertFalse("Не удалось залогиниться с данными созданного пользователя", response.path("success"));
    }
}