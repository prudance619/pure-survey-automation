package za.puresurvey.pages.compliance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Consent Records at /app/consent.
 * Note: Page heading in the app is "Consent Management" (not "Consent Records").
 */
public class ConsentRecordsPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Consent Records page.
     */
    public ConsentRecordsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Compliance from top nav, then Consent Records sub-link.
     */
    public ConsentRecordsPage openFromMenu() {
        openTopNavMenu("Compliance");
        clickTopNavSubLink("Consent Records");
        return waitForPageToLoad();
    }

    /**
     * Waits for Consent Records URL and heading.
     */
    public ConsentRecordsPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/consent");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Consent Management").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }

    /**
     * Returns true if Export CSV button is visible.
     */
    public boolean isExportCsvButtonDisplayed() {
        return waitHelper.isDisplayed(By.xpath("//button[contains(normalize-space(),'Export CSV')]"));
    }
}
