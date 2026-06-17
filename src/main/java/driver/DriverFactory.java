package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {


    public static void initDriver(String browserName) {

        BrowserType browser = BrowserType.valueOf(browserName.toUpperCase());
        WebDriver driver;

        switch (browser) {
            case FIREFOX ->
            {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case EDGE ->
            {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default ->
            {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverManager.setDriver(driver);
    }

    public static void quitDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}