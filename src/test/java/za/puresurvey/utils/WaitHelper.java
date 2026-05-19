package za.puresurvey.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import za.puresurvey.config.ConfigReader;

import java.time.Duration;

/**
 * Reusable explicit waits. Always prefer these methods over Thread.sleep().
 */
public class WaitHelper {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Creates a wait helper using timeout.seconds from config.properties.
     */
    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        int seconds = ConfigReader.getIntProperty("timeout.seconds");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    /**
     * Waits until the element is visible on the page, then returns it.
     */
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until a PageFactory element is visible, then returns it.
     */
    public WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the element can be clicked, then returns it.
     */
    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until a PageFactory element can be clicked.
     */
    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the element exists in the DOM (may not be visible yet).
     */
    public WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Returns true if the element is displayed; false if not found or hidden.
     */
    public boolean isDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Waits until clickable, then clicks. Helps avoid "element not clickable" errors.
     */
    public void safeClick(By locator) {
        WebElement element = waitForClickable(locator);
        element.click();
    }

    /**
     * Waits until a PageFactory element is clickable, then clicks it.
     */
    public void safeClick(WebElement element) {
        waitForClickable(element).click();
    }

    /**
     * Waits until the page URL contains the given text.
     */
    public void waitForUrlContains(String partialUrl) {
        wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    /**
     * Waits until login finished and the user is on the main app (not /app/login).
     * Important: "/app" alone also matches "/app/login", so we exclude "login" from the URL.
     */
    public void waitForLoggedInApp() {
        wait.until(webDriver -> {
            String url = webDriver.getCurrentUrl();
            return url.contains("/app") && !url.contains("login");
        });
    }
}
