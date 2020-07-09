package lessons.actions;

import lessons.Init;
import lessons.Locators;
import lessons.Waits;
import lessons.pages.LoginPage;
import org.junit.Assert;
import ru.sbtqa.tag.datajack.Stash;
import ru.yandex.qatools.htmlelements.element.Link;

import java.io.IOException;

public class DeepLinks {

    Init init = new Init();
    static Clicks clicks;

    static lessons.Waits waits = new Waits();
    static GetElements getE = new GetElements();

    public DeepLinks() throws IOException {
    }

//    public static void Login() throws InterruptedException, IOException {
//        Locators locators = new Locators();
//        clicks.click(getE.getWebElement(locators.enterButton));
//        waits.waitForPageToLoad();
//        getE.getWebElement(locators.loginEmail).sendKeys(Stash.getValue("username").toString());
//        getE.getWebElement(locators.loginPassword).sendKeys(Stash.getValue("password"));
//        clicks.click(getE.getWebElement(locators.loginEnterButton));
//        waits.waitForPageToLoad();
//        String valueToCheck = getE.getWebElement(locators.coursesLink).getText();
//        Stash.put("значение для проверки", valueToCheck);
//    }

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
}
