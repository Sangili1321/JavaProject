package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceLogin extends BasePage{

    private final By UserName = new By.ById("user-name");
    private final By Password = new By.ByCssSelector("input[name='password']");
    private final By Login = new By.ByXPath("//input[@data-test='login-button']");
    private final By Logo =new By.ByClassName("app_logo");
    private final By LockedMessage = new By.ByXPath("//h3[contains(text(),'Sorry, this user has been locked out.')]");

    public SauceLogin(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebDriverWait wait;

    public void LoginScenario(String username, String password){
        driver.findElement(UserName).sendKeys(username);
        driver.findElement(Password).sendKeys(password);
        driver.findElement(Login).click();
    }

    public String ValidateLockedUser(){
        var element = wait.until(ExpectedConditions.visibilityOfElementLocated(LockedMessage));
        return element.getText();
    }

    public boolean WaitUntilTheDashboardPage(){
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(Logo));
        return logo.getText().equals("Swag Labs");
    }
}
