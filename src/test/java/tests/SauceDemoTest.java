package tests;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceLogin;

//ctrl+alt+l -> to create a DPParallel.xml
public class SauceDemoTest extends  BaseTest{
    @Test
    public void ValidatePositiveFlow(){

        WebDriver driver = DriverManager.getDriver();
        driver.get("https://www.saucedemo.com/");
        SauceLogin login = new SauceLogin(driver);
        login.LoginScenario("standard_user","secret_sauce");
        Assert.assertTrue(login.WaitUntilTheDashboardPage());

    }
    @Test
    public void ValidateNegativeFlow(){

        WebDriver driver = DriverManager.getDriver();
        driver.get("https://www.saucedemo.com/");
        SauceLogin login = new SauceLogin(driver);
        login.LoginScenario("locked_out_user","secret_sauce");
        Assert.assertEquals(login.ValidateLockedUser(),"Epic sadface: Sorry, this user has been locked out.");

    }
}
