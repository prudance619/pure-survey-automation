package za.puresurvey.tests.administration;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.puresurvey.base.BaseTest;
import za.puresurvey.pages.administration.OrgUnitsPage;
import za.puresurvey.pages.administration.SettingsPage;
import za.puresurvey.pages.administration.UsersPage;
import za.puresurvey.pages.dashboard.DashboardPage;
import za.puresurvey.pages.login.LoginPage;

/**
 * Smoke tests for the Administration module.
 */
@Epic("Pure Survey")
@Feature("Administration")
public class AdministrationTest extends BaseTest {

    /**
     * Log in before each test.
     */
    @BeforeMethod(alwaysRun = true)
    public void loginToApp() {
        DashboardPage dashboard = new LoginPage(driver).open().loginWithConfigCredentials();
        dashboard.waitForPageToLoad();
    }

    /**
     * Users page loads.
     */
    @Test(description = "Users page loads")
    @Description("Open Users from Administration menu")
    public void usersPageLoads() {
        UsersPage page = new UsersPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Users");
        Assert.assertTrue(page.getCurrentUrl().contains("/users"));
        Assert.assertTrue(page.isAddUserButtonDisplayed(),
                "Add User button should be visible on Users page");
    }

    /**
     * Org Units page loads.
     */
    @Test(description = "Org Units page loads")
    @Description("Open Org Units from Administration menu")
    public void orgUnitsPageLoads() {
        OrgUnitsPage page = new OrgUnitsPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Organisation Units");
        Assert.assertTrue(page.getCurrentUrl().contains("/org-units"));
        Assert.assertTrue(page.isAddUnitButtonDisplayed(),
                "Add Unit button should be visible on Org Units page");
    }

    /**
     * Settings page loads.
     */
    @Test(description = "Settings page loads")
    @Description("Open Settings from Administration menu")
    public void settingsPageLoads() {
        SettingsPage page = new SettingsPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Settings");
        Assert.assertTrue(page.getCurrentUrl().contains("/settings"));
    }
}
