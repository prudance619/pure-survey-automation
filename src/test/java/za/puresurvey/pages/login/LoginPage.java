package za.puresurvey.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import za.puresurvey.config.ConfigReader;
import za.puresurvey.pages.BasePage;
import za.puresurvey.pages.dashboard.DashboardPage;

/**
 * Page Object for the login screen at /app/login.
 */
public class LoginPage extends BasePage {

    @FindBy(css = "input[type='email']")
    private WebElement emailField;

    @FindBy(css = "input[type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[normalize-space()='Sign in']")
    private WebElement signInButton;

    // TODO: Confirm exact invalid-login message text with your QA team
    @FindBy(xpath = "//*[contains(@class,'error') or contains(@role,'alert')]")
    private WebElement errorMessage;

    /**
     * Opens the login page using base.url from config.properties.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigates to the login URL.
     */
    public LoginPage open() {
        driver.get(ConfigReader.getProperty("base.url"));
        return this;
    }

    /**
     * Types the email address into the email field.
     */
    public LoginPage enterEmail(String email) {
        waitHelper.waitForVisible(emailField);
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    /**
     * Types the password into the password field.
     */
    public LoginPage enterPassword(String password) {
        waitHelper.waitForVisible(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    /**
     * Clicks Sign in and returns the Dashboard page object.
     */
    public DashboardPage clickSignIn() {
        waitHelper.safeClick(signInButton);
        waitHelper.waitForLoggedInApp();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.waitForPageToLoad();
        return dashboardPage;
    }

    /**
     * Full login in three steps: email, password, sign in.
     */
    public DashboardPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickSignIn();
    }

    /**
     * Login using username and password from config.properties.
     */
    public DashboardPage loginWithConfigCredentials() {
        String email = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        return login(email, password);
    }

    /**
     * Clicks Sign in without waiting for dashboard (used for negative tests).
     */
    public LoginPage clickSignInExpectingFailure() {
        waitHelper.safeClick(signInButton);
        // Wait briefly for login attempt to finish (stay on login or show error)
        try {
            waitHelper.waitForUrlContains("login");
        } catch (Exception e) {
            // If URL changed, caller assertions will handle it
        }
        return this;
    }

    /**
     * Returns true if an error/alert element is visible after a failed login.
     * TODO: Refine locator once the exact error markup is confirmed.
     */
    public boolean isErrorMessageDisplayed() {
        return waitHelper.isDisplayed(
                org.openqa.selenium.By.xpath("//*[contains(@class,'error') or contains(@role,'alert')]"));
    }

    /**
     * Returns true if the Sign in button is shown (user is still on login page).
     */
    public boolean isSignInButtonDisplayed() {
        return waitHelper.isDisplayed(
                org.openqa.selenium.By.xpath("//button[normalize-space()='Sign in']"));
    }
}
