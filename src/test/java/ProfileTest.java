import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.chrome.ChromeDriver;
import user.*;
import page.object.*;
import org.example.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProfileTest {

    public static String accessToken, refreshToken;
    public static User user;
    public WebDriver driver;
    public Profile personalProfilePage;
    public MainPage mainPage;
    public Login loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver, 10);
        loginPage = new Login(driver);
        personalProfilePage = new Profile(driver);
        driver.get(Endpoints.BASE_URL);
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem("accessToken", accessToken);
        localStorage.setItem("refreshToken", refreshToken);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeClass
    public static void beforeClass() {
        RestAssured.baseURI = Endpoints.BASE_URL;
        user = RandomUser.createNewRandomUser();
        Response response = UserSteps.createUser(user);
        accessToken = response.path("accessToken");
        refreshToken = response.path("refreshToken");
    }

    @AfterClass
    public static void afterClass() {
        UserSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Проверка выхода из профиля по кнопке 'Выход' в личном кабинете пользователя")
    public void checkProfileExitButton() {
        driver.get(Endpoints.BASE_URL + "/account");
        personalProfilePage.waitingForProfilePageLoading();
        personalProfilePage.menuItemExitButtonClick();
        loginPage.waitingForLoginFormLoading();
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        assertNull("Токен пользователя пустой", localStorage.getItem("accessToken"));
        assertEquals("Зашли на страницу логина после выхода", Endpoints.BASE_URL + Endpoints.LOGIN, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Проверка перехода на главную страницу после авторизации пользователя")
    public void checkTransitionToMainPageAfterUserLoggedIn() {
        driver.get(Endpoints.BASE_URL + "/account");
        personalProfilePage.waitingForProfilePageLoading();
        personalProfilePage.stellarBurgerLogotypeClick();
        mainPage.waitingForMainPageLoading();
        assertEquals("Перешли на главную страницу после авторизации", Endpoints.BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на главную страницу из личного кабинета по кнопке с логотипом в шапке")
    public void checkTransitionFromProfileByClickOnLogoButton() {
        driver.get(Endpoints.BASE_URL + "/account");
        personalProfilePage.waitingForProfilePageLoading();
        personalProfilePage.headerConstructorButtonClick();
        mainPage.waitingForMainPageLoading();
        assertEquals("Перешли на главную страницу после нажатия на логотип", Endpoints.BASE_URL + "/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в личный кабинет пользователя с главной страницы")
    public void checkFromMainToPersonalPassage() {
        mainPage.waitingForMainPageLoading();
        mainPage.clickOnProfileEnterButton();
        personalProfilePage.waitingForProfilePageLoading();
        assertEquals("Перешли в личный кабинет после нажатия кнопки 'Личный Кабинет' на главной странице", Endpoints.BASE_URL + Endpoints.PROFILE, driver.getCurrentUrl());
    }
}