package za.puresurvey.pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Org Units at /app/org-units.
 * Note: Page heading uses British spelling: "Organisation Units".
 */
public class OrgUnitsPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Org Units page.
     */
    public OrgUnitsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Administration from top nav, then Org Units sub-link.
     */
    public OrgUnitsPage openFromMenu() {
        openTopNavMenu("Administration");
        clickTopNavSubLink("Org Units");
        return waitForPageToLoad();
    }

    /**
     * Waits for Org Units URL and heading.
     */
    public OrgUnitsPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/org-units");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Organisation Units").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }

    /**
     * Returns true if Add Unit button is visible.
     */
    public boolean isAddUnitButtonDisplayed() {
        return waitHelper.isDisplayed(By.xpath("//button[contains(normalize-space(),'Add Unit')]"));
    }
}
