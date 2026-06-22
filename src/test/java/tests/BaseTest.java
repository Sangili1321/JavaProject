package tests;

import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {


    //High priority of browser setup will be given to CLI -Dbrowser value
    //Next it will give to TestNG
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        String browserValue = System.getProperty("browser", browser);
        DriverFactory.initDriver(browserValue);
    }
    /*
    When you use Driver.init() it will create an instance and set the thread local
    driver, then when you call getDriver it will get the driver for the execution.
    Even though the methods are static due to the thread local the driver is unique for each instance
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}