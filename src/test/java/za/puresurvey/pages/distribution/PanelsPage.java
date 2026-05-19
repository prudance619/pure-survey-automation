package za.puresurvey.pages.distribution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Panels at /app/panels.
 */
public class PanelsPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Panels page.
     */
    public PanelsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Distribution from top nav, then Panels sub-link.
     */
    public PanelsPage openFromMenu() {
        openDistributionFromTopNav();
        clickDistributionSubNav("Panels");
        return waitForPageToLoad();
    }

    /**
     * Waits for Panels URL and heading.
     */
    public PanelsPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/panels");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Panels").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
