import org.example.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import user.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.*;

public class RegistrationTest {
    @Rule
    public PrepareTest prepareTest = new PrepareTest();
    public WebDriver webDriver;
    public LogOut logOut;
    public Login login;
    public static String accessToken;

    @Before
    public void setUp() {
        webDriver = prepareTest.getWebDriver();
        RestAssured.baseURI = Endpoints.BASE_URL;
        logOut = new LogOut(webDriver);
        webDriver.get(Endpoints.BASE_URL + Endpoints.REGISTER);
        logOut.waitingForSignUpPageLoading();
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @AfterClass
    public static void afterClass() {
        UserSteps.deleteUser(accessToken);
    }


    @Test
    public void signUpTest() {
        User user = RandomUser.createNewRandomUser();
        login = new Login(webDriver);
        logOut.insertUserLogOutData(user);
        logOut.clickSignUpButton();
        login.waitingForLoginFormLoading();
        Assert.assertEquals("Перешли на страницу логина", Endpoints.BASE_URL + Endpoints.LOGIN, webDriver.getCurrentUrl());
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
        Assert.assertEquals("Остались на странице логина", Endpoints.BASE_URL + Endpoints.REGISTER, webDriver.getCurrentUrl());
        Response response = UserSteps.authUser(user);
        Assert.assertFalse("Не удалось залогиниться с данными созданного пользователя", response.path("success"));
    }
}