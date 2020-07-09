package lessons.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

public class HeaderBlock extends HtmlElement {

    @FindBy(xpath = "//div[contains(@class,'gb-top-menu')]")
    public WebElement topMenu;

    @FindBy(xpath = ".//*[@data-name='all_notification_banners']")
    public WebElement notifications;

    @FindBy(xpath = ".//*[@class='show-search-form']/svg")
    public WebElement searchButton;

    @FindBy(xpath = ".//*[@id='icon-schedule']")
    public WebElement calendar;

    @FindBy(xpath = ".//*[@href='/thanks']")
    public WebElement thanks;

    @FindBy(xpath = ".//*[@class='svg-icon icon-messages']")
    public WebElement messages;

    @FindBy(xpath = ".//*[@id='icon-more-icon']")
    public WebElement account;

    public void Search(String value) {

    }

}
