package za.puresurvey.pages.surveys;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.pages.BasePage;

/**
 * Page Object for Question Bank at /app/question-bank.
 */
public class QuestionBankPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement pageHeading;

    @FindBy(css = "input[placeholder='Search questions...']")
    private WebElement searchQuestionsField;

    @FindBy(xpath = "//button[contains(normalize-space(),'Add Question')]")
    private WebElement addQuestionButton;

    /**
     * Constructor for Question Bank page.
     */
    public QuestionBankPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Question Bank from the left side navigation.
     */
    public QuestionBankPage openFromSideNav() {
        clickSideNav("Question Bank");
        return waitForPageToLoad();
    }

    /**
     * Waits for Question Bank URL and heading.
     */
    public QuestionBankPage waitForPageToLoad() {
        waitHelper.waitForUrlContains("/question-bank");
        waitHelper.waitForVisible(By.tagName("h1"));
        dismissAllPopups();
        return this;
    }

    /**
     * Returns the page heading (expected: "Question Bank").
     */
    public String getPageHeadingText() {
        return getMainHeadingText();
    }

    /**
     * Returns true if the search questions field is visible.
     */
    public boolean isSearchFieldDisplayed() {
        return waitHelper.isDisplayed(By.cssSelector("input[placeholder='Search questions...']"));
    }

    /**
     * Returns true if the Add Question button is visible.
     */
    public boolean isAddQuestionButtonDisplayed() {
        return waitHelper.isDisplayed(
                By.xpath("//button[contains(normalize-space(),'Add Question')]"));
    }
}
