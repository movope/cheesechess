package de.movope.cheesechess.web;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AbstractPage {
    protected WebDriver driver;

    @FindBy(css = "label.error, .alert-error")
    private WebElement errors;

    public AbstractPage(WebDriver driver) {
        setDriver(driver);
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getErrors() {
        return errors.getText();
    }

    static void get(WebDriver driver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl","http://localhost:8080/game/") + relativeUrl;
        driver.get(url);
    }
}