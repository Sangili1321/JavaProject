package driver;

import org.openqa.selenium.WebDriver;

//This class is to control the driver initialization
//Added one more commit for fetch demo
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
}
