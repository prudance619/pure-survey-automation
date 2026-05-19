package za.puresurvey.pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Settings at /app/settings.
 */
public class SettingsPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Settings page.
     */
    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Administration from top nav, then Settings sub-link.
     */
    public SettingsPage openFromMenu() {
        openTopNavMenu("Administration");
        clickTopNavSubLink("Settings");
        return waitForPageToLoad();
    }

    /**
     * Waits for Settings URL and heading.
     */
    public SettingsPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/settings");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Settings").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
