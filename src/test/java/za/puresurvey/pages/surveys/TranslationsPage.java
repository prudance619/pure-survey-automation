package za.puresurvey.pages.surveys;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Survey Translations at /app/translations.
 */
public class TranslationsPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Translations page.
     */
    public TranslationsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Translations from the left side navigation.
     */
    public TranslationsPage openFromSideNav() {
        clickSideNav("Translations");
        return waitForPageToLoad();
    }

    /**
     * Waits for Translations URL and heading.
     */
    public TranslationsPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/translations");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Survey Translations").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
