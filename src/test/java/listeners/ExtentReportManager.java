package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import driver.DriverManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;//UI of the report
    public ExtentReports extent; //populate common info env, device, os
    //public ExtentTest test;//updating the status of the test case in the report
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        //do nothing...

    }


    public void onTestSuccess(ITestResult result) {
        test.set(extent.createTest(result.getName()));
        test.get().log(Status.PASS, result.getName() + "is PASSED");
        String base64Code = DriverManager.getBase64Screenshot();
        test.get().addScreenCaptureFromBase64String(base64Code, result.getName() +" Pass Screenshot");
        test.get().log(Status.PASS, "Test Case Passed",
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());

    }

    public void onTestFailure(ITestResult result) {
        test.set(extent.createTest(result.getName()));
        test.get().log(Status.FAIL, result.getName() + "is FAILED");
        test.get().log(Status.FAIL, "Failed due to "+ result.getThrowable());
        String base64Code = DriverManager.getBase64Screenshot();
        test.get().addScreenCaptureFromBase64String(base64Code, result.getName() +" Fail Screenshot");
        test.get().log(Status.FAIL, "Test Case Failed",
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
    }

    public void onTestSkipped(ITestResult result) {
        test.set(extent.createTest(result.getName()));
        test.get().log(Status.SKIP, result.getName() + "is SKIPPED");
    }


    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    //Execute before all the test methods
    public void onStart(ITestContext context) {

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+context.getName()+".html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("UI Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Computer Name","Sangili");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("OS", "Windows 11");
        extent.setSystemInfo("TesterName", "Sangili Durai");
        extent.setSystemInfo("Browser name","Chrome");

    }

    //Execute after all the test methods
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
