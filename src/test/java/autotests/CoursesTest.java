package autotests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@DisplayName("Проверка программы на курсе Тестировщик ПО")

public class CoursesTest extends BaseTest {

    @Test
    void checkCoursesPage() {
        //Пользователь переходит на сайт geekbrains.ru.
        driver.get("https://geekbrains.ru");

        //Нажать кнопку "Войти"
        WebElement buttonLogin = driver.findElement(By.cssSelector("[class=\"header-container\"] [href=\"/login\"]"));
        buttonLogin.click();

        //Переходит в курсы.
        WebElement buttonCourses = driver.findElement(By.cssSelector("[class*=\"main-page-hidden\"] [href=\"/courses\"]"));
        buttonCourses.click();

        //Закрываем рекламный баннер
        driver.findElement(By.cssSelector("div button svg[class=\"svg-icon icon-popup-close-button \"]")).click();

        //Переходит в профессию «Тестировщик ПО».
        WebElement buttonProfessionOfTestingPO = driver.findElement(By.cssSelector("[class=\"gu-profession-cards-grid__content\"] [href=\"/geek_university/qa-engineer\"]"));
        buttonProfessionOfTestingPO.click();

        //Узнаёт, сколько мест осталось.
        //Такой информации нет на сайте

        //Закрываем всплывающий фрейм с чатом
        driver.switchTo().frame("carrot-notification-frame");
        driver.findElement(By.cssSelector("[class=\"btn btn-text-primary\"] span")).click();
        driver.switchTo().frame("carrot-messenger-frame-closed");
        driver.findElement(By.cssSelector("[class=\"carrot-messenger-closed\"]")).click();

        //Смотрим историю успеха
        WebElement successStoryFirst = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-0\"]"));
        successStoryFirst.click();
        WebElement closeSuccessStoryFirst = driver.findElement(By.cssSelector("[id=\"success-story-popup-0\"] [class=\"gb-popup__close\"]"));
        closeSuccessStoryFirst.click();

        WebElement successStorySecond = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-1\"]"));
        successStorySecond.click();
        WebElement closeSuccessStorySecond = driver.findElement(By.cssSelector("[id=\"success-story-popup-1\"] [class=\"gb-popup__close\"]"));
        closeSuccessStorySecond.click();

        WebElement successStoryThird = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-2\"]"));
        successStoryThird.click();
        WebElement closeSuccessStoryThird = driver.findElement(By.cssSelector("[id=\"success-story-popup-2\"] [class=\"gb-popup__close\"]"));
        closeSuccessStoryThird.click();

        WebElement successStoryFourth = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-3\"]"));
        successStoryFourth.click();
        WebElement closeSuccessStoryFourth = driver.findElement(By.cssSelector("[id=\"success-story-popup-3\"] [class=\"gb-popup__close\"]"));
        closeSuccessStoryFourth.click();

        WebElement successStoryFifth = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-4\"]"));
        Actions action = new Actions(driver);
        action.moveToElement(successStoryFifth).click().build().perform();
        successStoryFifth.click();
        WebElement closeSuccessStoryFifth = driver.findElement(By.cssSelector("[id=\"success-story-popup-4\"] [class=\"gb-popup__close\"]"));
        closeSuccessStoryFifth.click();

        WebElement successStorySixth = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-5\"]"));
        action.moveToElement(successStorySixth).click().build().perform();
        successStorySixth.click();
        WebElement closeSuccessStorySixth = driver.findElement(By.cssSelector("[id=\"success-story-popup-5\"] [class=\"gb-popup__close\"]"));
        closeSuccessStorySixth.click();

        WebElement successStorySeventh = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-6\"]"));
        action.moveToElement(successStorySeventh).click().build().perform();
        successStorySeventh.click();
        WebElement closeSuccessStorySeventh = driver.findElement(By.cssSelector("[id=\"success-story-popup-6\"] [class=\"gb-popup__close\"]"));
        closeSuccessStorySeventh.click();

        WebElement successStoryEighth = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-7\"]"));
        action.moveToElement(successStoryEighth).click().build().perform();
        successStoryEighth.click();
        WebElement closeSuccessStoryEighth = driver.findElement(By.cssSelector("[id=\"success-story-popup-7\"] [class=\"gb-popup__close\"]"));
        closeSuccessStoryEighth.click();

        WebElement successStoryNinth = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-8\"]"));
        action.moveToElement(successStoryNinth).click().build().perform();
        successStoryNinth.click();
        WebElement closeSuccessStoryNinth = driver.findElement(By.cssSelector("[id=\"success-story-popup-8\"] [class=\"gb-popup__close\"]"));
        closeSuccessStoryNinth.click();

        WebElement successStoryTenth = driver.findElement(By.cssSelector("[class=\"card\"] [href=\"#success-story-popup-9\"]"));
        action.moveToElement(successStoryTenth).click().build().perform();
        successStoryTenth.click();
        WebElement closeSuccessStoryTenth = driver.findElement(By.cssSelector("[id=\"success-story-popup-9\"] [class=\"gb-popup__close\"]"));
        closeSuccessStoryTenth.click();

        action.moveToElement(successStoryFirst).click().build().perform();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Просматривает краткую программу на сайте.
        WebElement shortEducationProgramm = driver.findElement(By.cssSelector("h2[class=\"sc-kvZOFW dDxGvD\"]"));
        action.moveToElement(shortEducationProgramm).click().build().perform();
        shortEducationProgramm.isDisplayed();

        //Просматривает подробную программу.
        WebElement detailEducationProgramm = driver.findElement(By.cssSelector("[class=\"sc-jqCOkK euGcPk\"] svg"));
        detailEducationProgramm.click();

        WebElement closeDetailEducationProgramm = driver.findElement(By.cssSelector("[class=\"sc-fYxtnH pUCfV\"] [class=\"sc-tilXH cGUyNn\"]"));
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(closeDetailEducationProgramm));
            closeDetailEducationProgramm.click();

        //Смотрит свидетельство, подтверждающее результат.
        WebElement certificateSection = driver.findElement(By.cssSelector("[class=\"certificates-section__content container\"]"));
        certificateSection.isDisplayed();

        //Перетаскивает кнопку заказа обратного звонка.
        //Где вообще эта кнопка?? Ее нет на экране

        //Раскрывает ответы на часто задаваемые вопросы.
        WebElement helpDesk = driver.findElement(By.cssSelector("[class=\"site-footer__content\"] [href=\"https://geekbrains.zendesk.com/hc/ru\"]"));
        helpDesk.click();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        WebElement btnForStudentsOfProfessions = driver.findElement(By.cssSelector("[class=\"blocks-item\"] [href*=\"/hc/ru/categories/200038962\"]"));
        btnForStudentsOfProfessions.click();
        WebElement btnBasedQuestions = driver.findElement(By.cssSelector("[class=\"section\"] [href*=\"/hc/ru/sections/360004171814\"]"));
        btnBasedQuestions.click();
    }
}
