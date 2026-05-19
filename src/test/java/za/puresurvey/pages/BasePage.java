package za.puresurvey.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import za.puresurvey.utils.WaitHelper;

import java.util.List;

/**
 * Parent class for all page objects.
 * Holds the WebDriver, WaitHelper, and shared navigation actions used on many screens.
 */
public class BasePage {

    protected WebDriver driver;
    protected WaitHelper waitHelper;

    // Shared locators for navigation used across the app
    protected By gotItButton = By.xpath("//button[contains(.,'Got it')]");
    protected By sideNavLink = By.xpath("//nav//a[normalize-space()='%s']");
    protected By topNavDistributionButton = By.xpath("//button[normalize-space()='Distribution']");
    protected By distributionSubLink = By.xpath("//a[contains(normalize-space(),'%s')]");

    /**
     * Every page object receives the same WebDriver and initializes PageFactory.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Closes the first-run onboarding popup if it is visible.
     */
    public void dismissOnboardingIfPresent() {
        List<WebElement> buttons = driver.findElements(gotItButton);
        if (!buttons.isEmpty() && buttons.get(0).isDisplayed()) {
            waitHelper.safeClick(gotItButton);
        }
    }

    /**
     * Closes onboarding and any modal overlay that blocks clicks (tour, create panel, etc.).
     * Call this after page load when tests fail with "element click intercepted".
     */
    public void dismissAllPopups() {
        dismissOnboardingIfPresent();

        // Press Escape to close open modals/dialogs
        try {
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        } catch (Exception ignored) {
            // Body not ready — safe to ignore for this helper
        }

        // Click "Got it" again if a second onboarding step appears
        dismissOnboardingIfPresent();
    }

    /**
     * Clicks a link in the left side navigation (Dashboard, Surveys, etc.).
     */
    public void clickSideNav(String linkText) {
        By locator = By.xpath("//nav//a[normalize-space()='" + linkText + "']");
        waitHelper.safeClick(locator);
    }

    /**
     * Opens the Distribution area using the top navigation button.
     */
    public void openDistributionFromTopNav() {
        openTopNavMenu("Distribution");
    }

    /**
     * Clicks a sub-link under Distribution (e.g. Panels, Email Templates).
     */
    public void clickDistributionSubNav(String linkText) {
        clickTopNavSubLink(linkText);
    }

    /**
     * Clicks a top navigation button (Compliance, Integrations, Administration, etc.).
     */
    public void openTopNavMenu(String menuName) {
        dismissAllPopups();
        By locator = By.xpath("//button[normalize-space()='" + menuName + "']");
        waitHelper.safeClick(locator);
    }

    /**
     * Clicks a sub-link shown after opening a top nav menu.
     */
    public void clickTopNavSubLink(String linkText) {
        By locator = By.xpath("//a[contains(normalize-space(),'" + linkText + "')]");
        waitHelper.safeClick(locator);
    }

    /**
     * Returns the visible H1 heading text on the current page.
     * Uses a fresh lookup each time to avoid stale PageFactory elements.
     */
    public String getMainHeadingText() {
        return waitHelper.waitForVisible(By.tagName("h1")).getText();
    }

    /**
     * Returns the current browser URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
