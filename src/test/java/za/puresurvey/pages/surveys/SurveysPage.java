package za.puresurvey.pages.surveys;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for the Surveys list at /app/surveys.
 */
public class SurveysPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    @FindBy(xpath = "//button[contains(normalize-space(),'Create Survey')]")
    private WebElement createSurveyButton;

    @FindBy(css = "input[placeholder='Search surveys...']")
    private WebElement searchSurveysField;

    /**
     * Constructor for the Surveys list page.
     */
    public SurveysPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Surveys via side navigation and waits for the list page.
     */
    public SurveysPage openFromSideNav() {
        clickSideNav("Surveys");
        return waitForPageToLoad();
    }

    /**
     * Waits until the Surveys heading and URL are correct.
     */
    public SurveysPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/surveys");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading text (expected: "Surveys").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }

    /**
     * Clicks a status filter tab: All, Draft, Active, Closed, or Archived.
     * Uses filter button styling to avoid clicking unrelated buttons with the same label.
     */
    public SurveysPage clickStatusFilter(String statusName) {
        dismissAllPopups();
        By locator = By.xpath("//button[normalize-space()='" + statusName
                + "' and contains(@class,'rounded-md')]");
        waitHelper.safeClick(locator);
        return this;
    }

    /**
     * Types text into the survey search box.
     */
    public SurveysPage searchSurveys(String searchText) {
        waitHelper.waitForVisible(searchSurveysField);
        searchSurveysField.clear();
        searchSurveysField.sendKeys(searchText);
        return this;
    }

    /**
     * Opens the Create Survey panel/modal.
     */
    public SurveysPage clickCreateSurvey() {
        dismissAllPopups();
        waitHelper.safeClick(createSurveyButton);
        return this;
    }

    /**
     * Returns true if the Create Survey options panel shows "Start from Scratch".
     * TODO: Confirm if this text changes when the UI is updated.
     */
    public boolean isCreatePanelVisible() {
        return waitHelper.isDisplayed(
                By.xpath("//*[contains(normalize-space(),\"Start from Scratch\")]"));
    }

    /**
     * Returns true if a table column header with the given name is visible.
     */
    public boolean isTableHeaderVisible(String headerName) {
        By locator = By.xpath("//th[normalize-space()='" + headerName + "']");
        return waitHelper.isDisplayed(locator);
    }

    /**
     * Returns true if the search field is displayed.
     */
    public boolean isSearchFieldDisplayed() {
        return waitHelper.isDisplayed(By.cssSelector("input[placeholder='Search surveys...']"));
    }
}
