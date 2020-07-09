package lessons.pages;

import lessons.Init;
import lessons.blocks.NavigationBlock;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.io.IOException;

public class LoginPage {

    @FindBy(xpath = "//*[@name='user[email]']")
    public WebElement loginEmail;

    @FindBy(xpath = "//*[@name='user[password]']")
    public WebElement loginPassword;

    @FindBy(xpath = "//*[@id='new_user']//*[@value='Войти']")
    public WebElement loginEnterButton;

    public NavigationBlock navigationBlock;

    public LoginPage() throws IOException {
        Init init = new Init();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(init.getDriver())), this);
    }
}
