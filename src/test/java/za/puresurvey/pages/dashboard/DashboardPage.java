package za.puresurvey.pages.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;
import za.puresurvey.pages.surveys.SurveysPage;

/**
 * Page Object for the Dashboard (home) at /app.
 */
public class DashboardPage extends BasePage {

    // Greeting changes by time of day: "Good morning, Manager", etc.
    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    @FindBy(xpath = "//button[contains(normalize-space(),'New Survey')]")
    private WebElement newSurveyButton;

    @FindBy(xpath = "//button[normalize-space()='Filters']")
    private WebElement filtersButton;

    /**
     * Constructor — PageFactory fields are wired in BasePage.
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Waits for the dashboard to load after login.
     */
    public DashboardPage waitForPageToLoad() {
        waitHelper.waitForLoggedInApp();
        waitHelper.waitForVisible(By.xpath("//h1[contains(.,'Good')]"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the dashboard greeting text (e.g. "Good afternoon, Manager").
     */
    public String getGreetingText() {
        return waitHelper.waitForVisible(By.xpath("//h1[contains(.,'Good')]")).getText();
    }

    /**
     * Returns true if the time-based greeting is shown (Good morning/afternoon/evening).
     */
    public boolean isGreetingVisible() {
        String greeting = getGreetingText();
        return greeting.startsWith("Good");
    }

    /**
     * Returns true if the New Survey quick-action button is visible.
     */
    public boolean isNewSurveyButtonDisplayed() {
        dismissAllPopups();
        return waitHelper.isDisplayed(
                By.xpath("//button[contains(normalize-space(),'New Survey')]"));
    }

    /**
     * Returns true if the Filters button is visible.
     */
    public boolean isFiltersButtonDisplayed() {
        return waitHelper.isDisplayed(By.xpath("//button[normalize-space()='Filters']"));
    }

    /**
     * Opens the Surveys list using the left side navigation.
     */
    public SurveysPage goToSurveysViaSideNav() {
        dismissAllPopups();
        clickSideNav("Surveys");
        SurveysPage surveysPage = new SurveysPage(driver);
        return surveysPage.waitForPageToLoad();
    }
}
