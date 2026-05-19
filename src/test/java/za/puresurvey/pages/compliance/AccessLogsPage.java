package za.puresurvey.pages.compliance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Access Logs at /app/access-logs.
 */
public class AccessLogsPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Access Logs page.
     */
    public AccessLogsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Compliance from top nav, then Access Logs sub-link.
     */
    public AccessLogsPage openFromMenu() {
        openTopNavMenu("Compliance");
        clickTopNavSubLink("Access Logs");
        return waitForPageToLoad();
    }

    /**
     * Waits for Access Logs URL and heading.
     */
    public AccessLogsPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/access-logs");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Access Logs").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
