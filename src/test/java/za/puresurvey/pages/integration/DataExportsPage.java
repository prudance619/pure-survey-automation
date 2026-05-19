package za.puresurvey.pages.integration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Data Exports at /app/data-exports.
 */
public class DataExportsPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Data Exports page.
     */
    public DataExportsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Integrations from top nav, then Data Exports sub-link.
     */
    public DataExportsPage openFromMenu() {
        openTopNavMenu("Integrations");
        clickTopNavSubLink("Data Exports");
        return waitForPageToLoad();
    }

    /**
     * Waits for Data Exports URL and heading.
     */
    public DataExportsPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/data-exports");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Data Exports").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }

    /**
     * Returns true if Add Export button is visible.
     */
    public boolean isAddExportButtonDisplayed() {
        return waitHelper.isDisplayed(By.xpath("//button[contains(normalize-space(),'Add Export')]"));
    }
}
