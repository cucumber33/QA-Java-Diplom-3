import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import user.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import page.object.*;
import org.example.*;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    @Rule
    public PrepareTest prepareTest = new PrepareTest();
    public static WebDriver webDriver;
    public MainPage mainPage;
    public static String accessToken;
    public static User user;
    public static Login loginPage;
    @Before
    public void setUp() {
        webDriver = prepareTest.getWebDriver();
        mainPage = new MainPage(webDriver);
        loginPage = new Login(webDriver);
        webDriver.get(Endpoints.BASE_URL);
    }

    @BeforeClass
    public static void beforeClass() {
        RestAssured.baseURI = Endpoints.BASE_URL;
        user = RandomUser.createNewRandomUser();
        accessToken = UserSteps.createUser(user).path("accessToken");
    }

    @AfterClass
    public static void afterClass() {
        UserSteps.deleteUser(accessToken);
    }

    @Test
    public void checkLoginFromLoginPage() {
        webDriver.get(Endpoints.BASE_URL + Endpoints.LOGIN);
        assertions();
    }

    @Test
    @DisplayName("Проверка авторизации пользователя по логину и паролю - Личный кабинет")
    public void checkLoginFromMainPageProfileButton() {
        webDriver.get(Endpoints.BASE_URL);
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnProfileEnterButton();
        assertions();
    }

    @Test
    @DisplayName("Проверка авторизации пользователя по логину и паролю - Войти в аккаунт")
    public void checkLoginFromMainPageByEmailAndPassword() {
        webDriver.get(Endpoints.BASE_URL);
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnAccountEnterButton();
        assertions();
    }

    @Test
    @DisplayName("Проверка авторизации по кнопке - Войти - на странице - Регистрация")
    public void checkLoginFromSignUpPage() {
        webDriver.get(Endpoints.BASE_URL + Endpoints.REGISTER);
        LogOut logOut = new LogOut(webDriver);
        logOut.clickSignInLink();
        assertions();
    }

    @Test
    @DisplayName("Проверка авторизации через страницу восстановления пароля")
    public void checkLoginFromRecoveryPage() {
        webDriver.get(Endpoints.BASE_URL + Endpoints.FORGOT_PASS);
        PasswordRecovery passwordRecoveryPage = new PasswordRecovery(webDriver);
        passwordRecoveryPage.clickSignInLink();
        assertions();
    }

    @Step("Страница входа c правильным адресом")
    public static boolean loginPageCorrectUrl() {
        Login loginPage = new Login(webDriver);
        loginPage.waitingForLoginFormLoading();
        return webDriver.getCurrentUrl().equals(Endpoints.BASE_URL + Endpoints.LOGIN);
    }

    @Step("Главная страница с кнопкой 'Оформить заказ'")
    public static boolean mainPageIsLoadedAfterSuccessfulLogin() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.waitingForMainPageLoading();
        return mainPage.orderPlaceButtonIsDisplayed();
    }

    public static void assertions() {
        assertTrue("Зашли на страницу логина", loginPageCorrectUrl());
        loginPage.insertUserDataAndClickLoginButton(user);
        assertTrue("Вход выполнен успешно", mainPageIsLoadedAfterSuccessfulLogin());
    }
}