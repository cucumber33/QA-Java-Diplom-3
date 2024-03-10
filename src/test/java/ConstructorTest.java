import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends Temp {
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