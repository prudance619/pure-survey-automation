package za.puresurvey.pages.compliance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Opt-out Management at /app/opt-outs.
 */
public class OptOutManagementPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Opt-out Management page.
     */
    public OptOutManagementPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Compliance from top nav, then Opt-out Management sub-link.
     */
    public OptOutManagementPage openFromMenu() {
        openTopNavMenu("Compliance");
        clickTopNavSubLink("Opt-out Management");
        return waitForPageToLoad();
    }

    /**
     * Waits for Opt-out Management URL and heading.
     */
    public OptOutManagementPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/opt-outs");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Opt-out Management").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
