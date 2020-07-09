package lessons.pages;

import lessons.Init;
import lessons.blocks.HeaderBlock;
import lessons.blocks.NavigationBlock;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.io.IOException;
import java.util.List;

public class MainPage {
    public NavigationBlock navigationBlock;

    public HeaderBlock headerBlock;

    @FindAll({
            @FindBy(xpath = "//*[text()='Прошли тестирование']//parent::*//a[contains(@href,'/users') and @class='badc']")
    })
    public List<WebElement> UsersWithTests;

    public MainPage() throws IOException {
        Init init = new Init();
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(init.getDriver())), this);
    }
}
