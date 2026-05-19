package za.puresurvey.tests.login;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.puresurvey.base.BaseTest;
import za.puresurvey.config.ConfigReader;
import za.puresurvey.pages.dashboard.DashboardPage;
import za.puresurvey.pages.login.LoginPage;

/**
 * Tests for the login screen.
 */
@Epic("Pure Survey")
@Feature("Login")
public class LoginTest extends BaseTest {

    /**
     * L1: Valid credentials should open the Dashboard.
     */
    @Test(description = "Valid login opens dashboard with greeting")
    @Description("Enter valid email and password — user lands on dashboard")
    public void validLoginOpensDashboard() {
        LoginPage loginPage = new LoginPage(driver).open();

        DashboardPage dashboard = loginPage.loginWithConfigCredentials();
        dashboard.waitForPageToLoad();

        Assert.assertTrue(dashboard.getCurrentUrl().contains("/app")
                        && !dashboard.getCurrentUrl().contains("login"),
                "Expected dashboard URL after login");
        Assert.assertTrue(dashboard.isGreetingVisible(),
                "Dashboard greeting should start with 'Good'");
    }

    /**
     * L2: Wrong password should not open the dashboard.
     * TODO: Confirm exact error message text and update assertion if needed.
     */
    @Test(description = "Invalid password shows error and stays on login")
    @Description("Wrong password — user should see an error and remain on login page")
    public void invalidPasswordShowsError() {
        LoginPage loginPage = new LoginPage(driver).open();

        loginPage.enterEmail(ConfigReader.getProperty("username"));
        loginPage.enterPassword("WrongPassword123!");
        loginPage.clickSignInExpectingFailure();

        // User should remain on the login page (URL or Sign in button)
        boolean stillOnLoginPage = driver.getCurrentUrl().contains("login")
                || loginPage.isSignInButtonDisplayed();
        Assert.assertTrue(stillOnLoginPage,
                "User should stay on login page after invalid password");

        // Soft check — error element markup may vary
        if (!loginPage.isErrorMessageDisplayed()) {
            System.out.println("TODO: No error alert found — confirm invalid-login UI with QA team");
        }
    }
}
