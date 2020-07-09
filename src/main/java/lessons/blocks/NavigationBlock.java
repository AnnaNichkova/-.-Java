package lessons.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

public class NavigationBlock extends HtmlElement {

    @FindBy(xpath = "//*[@id='nav']//*[@href='/courses']")
    public Link courses;


}
