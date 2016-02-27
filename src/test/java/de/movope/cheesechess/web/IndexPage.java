
package de.movope.cheesechess.web;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class IndexPage extends AbstractPage {

    private WebElement gameId;

    @FindBy(css = "input[type=submit]")
    private WebElement submit;

    @FindBy(xpath = "//div[@id='gameIds']/ul/li")
    private List<WebElement> gameIds;

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    public static IndexPage to(WebDriver driver) {
        get(driver, "overview");
        return PageFactory.initElements(driver, IndexPage.class);
    }

    public <T> T createMessage(Class<T> resultPage, String gameId) {
        this.gameId.sendKeys(gameId);
        this.submit.click();
        return PageFactory.initElements(driver, resultPage);
    }

    public List<WebElement> getGameIds() {
        return gameIds;
    }
}
