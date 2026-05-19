package za.puresurvey.pages.distribution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Distribution Lists at /app/lists.
 */
public class DistributionListPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Distribution Lists page.
     */
    public DistributionListPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Distribution from top nav, then Distribution Lists sub-link.
     */
    public DistributionListPage openFromMenu() {
        openDistributionFromTopNav();
        clickDistributionSubNav("Distribution Lists");
        return waitForPageToLoad();
    }

    /**
     * Waits for Distribution Lists URL and heading.
     */
    public DistributionListPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/lists");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Distribution Lists").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
