package za.puresurvey.pages.compliance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Audit Log at /app/audit-log.
 */
public class AuditLogPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Audit Log page.
     */
    public AuditLogPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Compliance from top nav, then Audit Log sub-link.
     */
    public AuditLogPage openFromMenu() {
        openTopNavMenu("Compliance");
        clickTopNavSubLink("Audit Log");
        return waitForPageToLoad();
    }

    /**
     * Waits for Audit Log URL and heading.
     */
    public AuditLogPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/audit-log");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Audit Log").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
