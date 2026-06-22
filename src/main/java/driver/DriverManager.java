package driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

//This class is to control the driver initialization
public final class DriverManager {

    private DriverManager() {}

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    static void unload() {
        driver.remove();
    }

    public static String getBase64Screenshot() {
        // Capture screenshot as Base64 string
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}