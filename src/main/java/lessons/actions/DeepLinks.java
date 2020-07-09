package lessons.actions;

import lessons.Init;
import lessons.Locators;
import lessons.Waits;
import lessons.pages.LoginPage;
import ru.sbtqa.tag.datajack.Stash;

import java.io.IOException;

public class DeepLinks {

    Init init = new Init();
    static Clicks clicks;

    static {
        try {
            clicks = new Clicks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static lessons.Waits waits = new Waits();
    static GetElements getE = new GetElements();

    public DeepLinks() throws IOException {
    }

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
}
