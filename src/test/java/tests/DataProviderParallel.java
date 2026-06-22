package tests;

import driver.DriverManager;
import listeners.ExtentReportManager;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SauceLogin;
import utils.ExcelReader;

import java.io.IOException;

@Listeners(ExtentReportManager.class)//We will always use from XML file
public class DataProviderParallel extends BaseTest{


    @Test(dataProvider = "saucelogin")
    public void ValidatePositiveFlow(String username, String password){

        WebDriver driver = DriverManager.getDriver();
        driver.get("https://www.saucedemo.com/");
        SauceLogin login = new SauceLogin(driver);
        login.LoginScenario(username,password);
        //Assert.assertTrue(login.WaitUntilTheDashboardPage());

    }

    @DataProvider(name = "saucelogin", parallel = true)
    public String[][] getData() {
        return new String[][]{
                { "standard_user",   "secret_sauce" },
                { "locked_out_user", "secret_sauce" },
                { "problem_user",    "secret_sauce" },
                { "performance_glitch_user",    "secret_sauce" },
                { "error_user",    "secret_sauce" },
                { "visual_user",    "secret_sauce" }

        };
    }

    @DataProvider(name="login")
    public String[][] getExcelData() throws IOException {
        ExcelReader reader = new ExcelReader();
        return reader.getData("./src/test/resources/ExcelData/LoginData.xlsx");
    }
}
