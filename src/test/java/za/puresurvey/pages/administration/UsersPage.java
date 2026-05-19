package za.puresurvey.pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Users at /app/users.
 */
public class UsersPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Users page.
     */
    public UsersPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Administration from top nav, then Users sub-link.
     */
    public UsersPage openFromMenu() {
        openTopNavMenu("Administration");
        clickTopNavSubLink("Users");
        return waitForPageToLoad();
    }

    /**
     * Waits for Users URL and heading.
     */
    public UsersPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/users");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Users").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }

    /**
     * Returns true if Add User button is visible.
     */
    public boolean isAddUserButtonDisplayed() {
        return waitHelper.isDisplayed(By.xpath("//button[contains(normalize-space(),'Add User')]"));
    }
}
