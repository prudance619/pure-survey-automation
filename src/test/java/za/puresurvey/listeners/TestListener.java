package za.puresurvey.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import za.puresurvey.base.BaseTest;
import za.puresurvey.utils.ScreenshotHelper;

/**
 * TestNG listener that saves a screenshot when any test fails.
 */
public class TestListener implements ITestListener {

    /**
     * Called when a test fails — captures a screenshot if WebDriver is available.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();

        if (testInstance instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testInstance;
            WebDriver driver = baseTest.getDriver();

            if (driver != null) {
                String path = ScreenshotHelper.capture(driver, result.getName());
                if (!path.isEmpty()) {
                    System.out.println("Screenshot saved: " + path);
                }
            }
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Not used — left empty on purpose
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Not used
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Not used
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }

    @Override
    public void onStart(ITestContext context) {
        // Not used
    }

    @Override
    public void onFinish(ITestContext context) {
        // Not used
    }
}
