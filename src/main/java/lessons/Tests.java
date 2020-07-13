package lessons;

import io.qameta.allure.Step;
import lessons.actions.Clicks;
import lessons.actions.DeepLinks;
import lessons.actions.GetElements;
import lessons.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.sbtqa.tag.datajack.Stash;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static lessons.actions.DeepLinks.checkReferralLink;

public class Tests {
    Locators locators = new Locators();
    Init init = new Init();
    static Clicks clicks;

    static lessons.Waits waits = new Waits();
    static GetElements getE = new GetElements();

    public Tests() throws IOException {
    }

    @Test
    public void test1 () throws Exception{
        init.getDriver().navigate().to("https://geekbrains.ru/");
//        WebElement enterButton = init.getDriver().findElement(By.xpath(new Locators().enterButton));
//        waits.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(new Locators().enterButton)));
//        clicks.click(enterButton);
        //init.getDriver().findElement(By.xpath(new Locators().enterButton)).click();
      //  waits.waitForPageToLoad();

        //Положим хранилище логина от сайта гикбрейнс
        Stash.put("username",init.props.getProperty("geekbrains.login"));
        //Аналогично пароль
        Stash.put("password",init.props.getProperty("geekbrains.password"));

        Login();
        checkCoursesVal();

        //Добавим ожидание загрузки страницы
        //waits.waitForPageToLoad();

        //Запомним логин и пароль
        //Логин и пароль заданы в properties files
        //getWebElement(locators.loginEmail).sendKeys(init.props.getProperty("geekbrains.login"));
        //getWebElement(locators.loginPassword).sendKeys(init.props.getProperty("geekbrains.password"));

        //Аналогично делаем нажатие на кнопку "Войти"
        //click(getWebElement(locators.loginEnterButton));

        //Инициализируем LoginPage
        checkSlotsCount();
        WebElement program = navigateToFullProgramm();

        //Реализация переноса кнопки обратного звонка
        WebElement callButton = getWebElement(locators.callBackButtonId);
        Actions actions = new Actions(init.getDriver());
        actions.dragAndDrop(callButton, program).perform();
    }

    @Step
    private WebElement navigateToFullProgramm() throws InterruptedException {
        //Реализация просмотра полной программы
        WebElement program = getWebElement(locators.programSizeSubtitle);

        //Чтобы проскролить в коде до элемента programSizeSubtitle, копируем из метода ниже реализацию scrollIntoView
        JavascriptExecutor executor = (JavascriptExecutor) Init.getDriver();
        executor.executeScript("arguments[0].scrollIntoView(); ", program);

        //Добавляем ссылку на полную программу
        clicks.click(getWebElement(locators.fullProgram));

        //Добавим ожидание загрузки страницы
        waits.waitForPageToLoad();
        return program;
    }

    @Step
    private void checkSlotsCount() throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage();

        //Вводим логин и пароль с помощью Page Object
        //loginPage.loginEmail.sendKeys();
        //loginPage.loginPassword.sendKeys();

        //Аналогично с кнопкой "Войти"
        clicks.click(loginPage.loginEnterButton);

        //Добавим ожидание загрузки страницы
        waits.waitForPageToLoad();

        //Добавим нажатие на иконку курсов
        clicks.click(loginPage.navigationBlock.courses);

        //Добавим ожидание загрузки страницы
        waits.waitForPageToLoad();

        //Добавляем ссылку на профессию тестировщик
        clicks.click(getWebElement(locators.qaEngineerLink));

        //Добавим ожидание загрузки страницы
        waits.waitForPageToLoad();

        //Проверка количества свободных слотов
        String emptySlots = getWebElement(locators.slotsCount).getText();
    }

    @Step
    private void checkCoursesVal() {
        Assert.assertTrue(Stash.getValue("значение для проверки").equals("Курсы"));
    }

    @Test
    public void test2() throws InterruptedException, IOException {
        Stash.put("username",init.props.getProperty("geekbrains.login"));
        Stash.put("password",init.props.getProperty("geekbrains.password"));
        Login();
        checkReferralLink("Тестирование", "http://geekbrains.ru/go/8bAkt6");

    }

        //Найдем элемент с помощью метода waitAndGetWebElementsLite
    private WebElement getWebElement(String xpath) {
        return waits.waitAndGetWebElementsLite(xpath, Waits.medium_wait, Waits.polling_time).get(0);
    }

    @Step
    public static void Login() throws InterruptedException, IOException {
        Locators locators = new Locators();
        clicks.click(getE.getWebElement(locators.enterButton));
        waits.waitForPageToLoad();
        getE.getWebElement(locators.loginEmail).sendKeys(Stash.getValue("username").toString());
        getE.getWebElement(locators.loginPassword).sendKeys(Stash.getValue("password"));
        clicks.click(getE.getWebElement(locators.loginEnterButton));
        waits.waitForPageToLoad();
        String valueToCheck = getE.getWebElement(locators.coursesLink).getText();
        Stash.put("значение для проверки", valueToCheck);
    }

    @Step
    public static void checkReferralLink (String name, String Link) throws InterruptedException {
        waits.waitForPageToLoad();
        Locators locators = new Locators();
        clicks.click(getE.getWebElement(locators.topMenu));
        waits.waitForPageToLoad();
        clicks.click(getE.getWebElement(locators.referrals));
        waits.waitForPageToLoad();
        clicks.click(getE.getWebElement(locators.referralsDropdown));
        clicks.click(getE.getWebElement("//*[text()='" + name + "']"));
        String ref_link = getE.getWebElement(locators.refLink).getText();
        Assert.assertEquals(Link, ref_link);
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

    public void switchToFrame() throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) init.getDriver();
        if (FrameToSwitch != null) {
            long startTime = new Waits().Now();
            while (((new Waits().Now() - startTime) / 1000) < Waits.medium_wait) {
                init.getDriver().switchTo().defaultContent();
                Thread.sleep(500);
                init.getDriver().switchTo().frame(FrameToSwitch);
                if (jsExecutor.executeScript("return self.name").equals(FrameToSwitch)) {
                    break;
                }
            }
        }
        Assert.assertTrue(jsExecutor.executeScript("return self.name").equals(FrameToSwitch));
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
