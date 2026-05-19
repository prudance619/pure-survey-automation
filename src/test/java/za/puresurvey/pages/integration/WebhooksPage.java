package za.puresurvey.pages.integration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Webhooks at /app/webhook-configs.
 */
public class WebhooksPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Webhooks page.
     */
    public WebhooksPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Integrations from top nav, then Webhooks sub-link.
     */
    public WebhooksPage openFromMenu() {
        openTopNavMenu("Integrations");
        clickTopNavSubLink("Webhooks");
        return waitForPageToLoad();
    }

    /**
     * Waits for Webhooks URL and heading.
     */
    public WebhooksPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/webhook-configs");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Webhook Endpoints").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
