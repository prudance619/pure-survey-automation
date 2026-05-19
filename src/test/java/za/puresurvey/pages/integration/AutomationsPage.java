package za.puresurvey.pages.integration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Automations at /app/event-triggers.
 */
public class AutomationsPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Automations page.
     */
    public AutomationsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Integrations from top nav, then Automations sub-link.
     */
    public AutomationsPage openFromMenu() {
        openTopNavMenu("Integrations");
        clickTopNavSubLink("Automations");
        return waitForPageToLoad();
    }

    /**
     * Waits for Automations URL and heading.
     */
    public AutomationsPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/event-triggers");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Automations").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
