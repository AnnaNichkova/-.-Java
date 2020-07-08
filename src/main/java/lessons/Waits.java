package lessons;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Waits {

    public static int small_wait = 3;
    public static int medium_wait = 10;
    public static int big_wait = 30;
    public static int polling_time = 1;

    WebDriverWait wait = new WebDriverWait(Init.getDriver(), medium_wait);

    public long Now() {
        return new Date().getTime();
    }

    public void staticWait(double pollingTime) {
        try {
            Thread.sleep((int) (pollingTime * 1000));
        } catch (InterruptedException interrupt) {
            System.out.println("проблема с потоком");
        }
    }

    public List<WebElement> waitAndGetWebElementsLite(String xpath, int timeout, double pollingTime) {
        List<WebElement> webElements = new ArrayList<>();
        long startTime = Now();
        while ((Now() - startTime) / 1000 < timeout) {
            try {
                webElements = Init.getDriver().findElements(By.xpath(xpath));
            } catch (InvalidSelectorException e) {
                System.out.println("некорректный синтаксис локатора");
                throw e;
            }
            if ((!webElements.isEmpty()) && webElements.get(0).isEnabled()) {
                return webElements;
            }
            staticWait(pollingTime);
        }
        //можем вернуть пустой список
        return webElements;
    }

    public void waitNotElements(String xpath, int timeout, double pollingTime, int startWaitForPresent) {
        List<WebElement> elements = null;
        List<WebElement> invisibleElements = null;
        if (waitAndGetWebElementsLite(xpath, startWaitForPresent, pollingTime).isEmpty()) {
            System.out.println("Элементы " + xpath + " не появились");
            return;
        }
        long startTime = Now();
        while (!((Now() - startTime) / 1000 < timeout)) {
            staticWait(pollingTime);
            elements = Init.getDriver().findElements(By.xpath(xpath));
            invisibleElements = elements.stream().filter(elem -> !elem.isDisplayed()).collect(Collectors.toList());
            if (elements.isEmpty() || (elements.size() == invisibleElements.size())) {
                break;
            }
        }
        Assert.assertTrue("элементы " + xpath + " появились и не исчезли", elements.isEmpty());
    }

    public void waitForPageToLoad() {
        long startTime = Now();
        while (!((Now() - startTime) > medium_wait)) {
            JavascriptExecutor js = (JavascriptExecutor) Init.getDriver();
            if (js.executeScript("return document.readyState").equals("complete")) {
                break;
            }
            staticWait(polling_time);
        }
    }
}