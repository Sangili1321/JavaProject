package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

//Ctrl + Alt + Shift + L
public class ScreenshotUtils {
    private WebDriver driver;
    private static final org.apache.logging.log4j.Logger Logger = LoggerUtil.getLogger(ScreenshotUtils.class);
    public ScreenshotUtils(WebDriver driver){
        this.driver = driver;
    }

    public void DoScreenShot() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE),new File("C:\\Users\\sduraia\\AllProjects\\CucumberProject\\target\\Screenshots"));
        //Logger.

    }
}
