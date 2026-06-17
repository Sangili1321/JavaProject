package CucumberSetupDemo.BrowserSetUp;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browser {

        public static WebDriver driver;
        public static FirefoxOptions optionf = new FirefoxOptions();

        /* By Sangili_Durai_A
         * This method is used to initialize chrome_driver and will return that driver*/
        public static WebDriver getDriver() throws IOException {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            return driver;


        }

        /* By Sangili_Durai_A
         * This method is used to initialize gecko_driver and will return that driver*/
        public static WebDriver getFireFoxDriver() {
            optionf.addArguments("--disable-notifications", "incognito");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(optionf);
            driver.manage().window().maximize();
            return driver;

        }

        /* By Sangili_Durai_A
         * This method is used to initialize edge driver and will return that driver*/
        public static WebDriver getEdgeDriver() {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            return driver;

        }
    }

