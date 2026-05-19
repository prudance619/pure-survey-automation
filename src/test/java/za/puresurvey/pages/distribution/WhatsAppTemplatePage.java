package za.puresurvey.pages.distribution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for WhatsApp Templates at /app/whatsapp-templates.
 */
public class WhatsAppTemplatePage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for WhatsApp Templates page.
     */
    public WhatsAppTemplatePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Distribution from top nav, then WhatsApp Templates sub-link.
     */
    public WhatsAppTemplatePage openFromMenu() {
        openDistributionFromTopNav();
        clickDistributionSubNav("WhatsApp Templates");
        return waitForPageToLoad();
    }

    /**
     * Waits for WhatsApp Templates URL and heading.
     */
    public WhatsAppTemplatePage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/whatsapp-templates");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "WhatsApp Templates").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
