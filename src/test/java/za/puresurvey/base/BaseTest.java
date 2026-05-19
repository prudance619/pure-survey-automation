package za.puresurvey.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import za.puresurvey.utils.DriverFactory;

/**
 * Parent class for all test classes.
 * Starts the browser before each test and closes it afterwards.
 */
public class BaseTest {

    protected WebDriver driver;

    /**
     * Returns the WebDriver (used by listeners, e.g. for screenshots).
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Opens a new browser before every @Test method.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.createDriver();
    }

    /**
     * Closes the browser after every @Test method (pass or fail).
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
