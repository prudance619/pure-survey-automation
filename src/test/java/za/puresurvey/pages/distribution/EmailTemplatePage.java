package za.puresurvey.pages.distribution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Email Templates at /app/email-templates.
 */
public class EmailTemplatePage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Email Templates page.
     */
    public EmailTemplatePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Distribution from top nav, then Email Templates sub-link.
     */
    public EmailTemplatePage openFromMenu() {
        openDistributionFromTopNav();
        clickDistributionSubNav("Email Templates");
        return waitForPageToLoad();
    }

    /**
     * Waits for Email Templates URL and heading.
     */
    public EmailTemplatePage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/email-templates");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Email Templates").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
