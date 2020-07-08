package lessons;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Tests {

    String FrameToSwitch;
    Waits waits = new Waits();
    Locators locators = new Locators();
    Init init = new Init();

    public Tests() throws IOException {
    }

    public void test1 () throws Exception{
        init.getDriver().navigate().to("https://geekbrains.ru/");
        WebElement enterButton = init.getDriver().findElement(By.xpath(new Locators().enterButton));
        waits.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(new Locators().enterButton)));
        click(enterButton);
        //init.getDriver().findElement(By.xpath(new Locators().enterButton)).click();
        waits.waitForPageToLoad();

        //Запомним логин и пароль
        //Логин и пароль заданы в properties files
        getWebElement(locators.loginEmail).sendKeys(init.props.getProperty("geekbrains.login"));
        getWebElement(locators.loginPassword).sendKeys(init.props.getProperty("geekbrains.password"));

        //Аналогично делаем нажатие на кнопку "Войти"
        click(getWebElement(locators.loginEnterButton));

        //Добавим ожидание загрузки страницы
        waits.waitForPageToLoad();

        //Добавим нажатие на иконку курсов
        click(getWebElement(locators.coursesLink));

        //Добавим ожидание загрузки страницы
        waits.waitForPageToLoad();

        //Добавляем ссылку на профессию тестировщик
        click(getWebElement(locators.qaEngineerLink));

        //Добавим ожидание загрузки страницы
        waits.waitForPageToLoad();

        //Проверка количества свободных слотов
        String emptySlots = getWebElement(locators.slotsCount).getText();

        //Реализация просмотра полной программы
        WebElement program = getWebElement(locators.programSizeSubtitle);

        //Чтобы проскролить в коде до элемента programSizeSubtitle, копируем из метода ниже реализацию scrollIntoView
        JavascriptExecutor executor = (JavascriptExecutor)Init.getDriver();
        executor.executeScript("arguments[0].scrollIntoView(); ", program);

        //Добавляем ссылку на полную программу
        click(getWebElement(locators.fullProgram));

        //Добавим ожидание загрузки страницы
        waits.waitForPageToLoad();

        //Реализация переноса кнопки обратного звонка
        WebElement callButton = getWebElement(locators.callBackButtonId);
        Actions actions = new Actions(init.getDriver());
        actions.dragAndDrop(callButton, program).perform();
    }

        //Найдем элемент с помощью метода waitAndGetWebElementsLite
    private WebElement getWebElement(String xpath) {
        return waits.waitAndGetWebElementsLite(xpath, Waits.medium_wait, Waits.polling_time).get(0);
    }

//    //Проверка таблицы со статистикой игроков
//    public void test2 () throws Exception {
//        init.getDriver().navigate().to("https://www.championat.com/football/worldcup/1589/statistic/player/bombardir.html");
//        waits.waitForPageToLoad();
//        WebElement cell = getFirstOneCellByValueFromTable("Голы", "6");
//
//        List<WebElement> goals = waits.waitAndGetWebElementsLite(locators.goalsColumnElements, Waits.medium_wait, Waits.polling_time);
//        checkNumbersSorting(goals, true);
//        filterOutByColumnName("Голы");
//        checkNumbersSorting(goals, true);
//        filterOutByColumnName("Игрок");
//        List<WebElement> players = waits.waitAndGetWebElementsLite(locators.playersColumnElements, Waits.medium_wait, Waits.polling_time);
//        checkStringSorting(players, true);
//        check_row_with_values_exists_in_table("Игрок, Амплуа, Пенальти", "Ахмед Муса, нападающий, 0", true);
//
//    }

    //Реализация клика
    public void click (WebElement element) throws InterruptedException {
        Actions actions = new Actions(Init.getDriver());
        JavascriptExecutor executor = (JavascriptExecutor)Init.getDriver();
        boolean isClicked = false;
        long startTime = new Waits().Now();
        while (((new Waits().Now() - startTime)/1000)<Waits.medium_wait) {
            try {
                Assert.assertTrue(element.isDisplayed());
                actions.moveToElement(element).build().perform();
                executor.executeScript("arguments[0].scrollIntoView(); ", element);
                executor.executeScript("arguments[0].focus(); ", element);
                executor.executeScript("arguments[0].click(); ", element);
                isClicked = true;
                break;
            } catch (Exception e) {
                LOGGER.info ("Клик не отрабатывает с первого раза - делаю повторный клик");
            }
            Thread.sleep(500);
        }
        Assert.assertTrue("Не произошло клика", isClicked);
    }

    private WebElement getFirstOneCellByValueFromTable(String columnName, String value) throws Exception {
        Waits waits = new Waits();
        //String loaderXPATH = "";
        //waits.waitNotElements(loaderXPATH, Waits.medium_wait, Waits.polling_time, Waits.small_wait);
        waits.waitForPageToLoad();
        List<WebElement> headers = waits.waitAndGetWebElementsLite("//table//th/span", Waits.medium_wait, Waits.polling_time);
        List<WebElement> cells = null;
        for (int i = 1; i <= headers.size() ; i++) {
            if (headers.get(i - 1).getText().equals(columnName)) {
                List<WebElement> cellsInColumn = waits.waitAndGetWebElementsLite("//table//td[contains (@class, 'pstat__td') or contains(@class, 'big')][" + String.valueOf(i+2) + "]", Waits.medium_wait, Waits.polling_time);
                cells = cellsInColumn.stream().filter((element) -> element.getText().equals(value)).collect(Collectors.toList());
                break;
            }
        }
        if (!cells.isEmpty()) {
            return cells.get(0);
        } else {
            throw new Exception("Нет нужной ячейки");
        }
    }
}
