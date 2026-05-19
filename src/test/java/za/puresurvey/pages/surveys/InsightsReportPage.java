package za.puresurvey.pages.surveys;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Insight Reports at /app/insight-reports.
 */
public class InsightsReportPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    /**
     * Constructor for Insight Reports page.
     */
    public InsightsReportPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Insight Reports from the left side navigation.
     */
    public InsightsReportPage openFromSideNav() {
        clickSideNav("Insight Reports");
        return waitForPageToLoad();
    }

    /**
     * Waits for Insight Reports URL and heading.
     */
    public InsightsReportPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/insight-reports");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Insight Reports").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }
}
