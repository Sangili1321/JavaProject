package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//we can use TestListenerAdapter class also
public class TestListener implements ITestListener {

    //Execute before each test methods
    public void onTestStart(ITestResult result) {

        System.out.println("Test Method started");
    }


    public void onTestSuccess(ITestResult result) {
        // not implemented
        System.out.println("Test Method is passed");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Method is failed");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Method is skipped");
    }


    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    //Execute before all the test methods
    public void onStart(ITestContext context) {
        System.out.println("Tests started");
    }

    //Execute after all the test methods
    public void onFinish(ITestContext context) {
        System.out.println("Tests completed");
    }
}
