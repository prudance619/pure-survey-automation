package za.puresurvey.tests.integration;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.puresurvey.base.BaseTest;
import za.puresurvey.pages.dashboard.DashboardPage;
import za.puresurvey.pages.integration.AutomationsPage;
import za.puresurvey.pages.integration.DataExportsPage;
import za.puresurvey.pages.integration.WebhooksPage;
import za.puresurvey.pages.login.LoginPage;

/**
 * Smoke tests for the Integrations module.
 */
@Epic("Pure Survey")
@Feature("Integrations")
public class IntegrationTest extends BaseTest {

    /**
     * Log in before each test.
     */
    @BeforeMethod(alwaysRun = true)
    public void loginToApp() {
        DashboardPage dashboard = new LoginPage(driver).open().loginWithConfigCredentials();
        dashboard.waitForPageToLoad();
    }

    /**
     * Automations page loads.
     */
    @Test(description = "Automations page loads")
    @Description("Open Automations from Integrations menu")
    public void automationsPageLoads() {
        AutomationsPage page = new AutomationsPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Automations");
        Assert.assertTrue(page.getCurrentUrl().contains("/event-triggers"));
    }

    /**
     * Webhooks page loads.
     */
    @Test(description = "Webhooks page loads")
    @Description("Open Webhooks from Integrations menu")
    public void webhooksPageLoads() {
        WebhooksPage page = new WebhooksPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Webhook Endpoints",
                "Menu link is 'Webhooks' but page heading is 'Webhook Endpoints'");
        Assert.assertTrue(page.getCurrentUrl().contains("/webhook-configs"));
    }

    /**
     * Data Exports page loads.
     */
    @Test(description = "Data Exports page loads")
    @Description("Open Data Exports from Integrations menu")
    public void dataExportsPageLoads() {
        DataExportsPage page = new DataExportsPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Data Exports");
        Assert.assertTrue(page.getCurrentUrl().contains("/data-exports"));
        Assert.assertTrue(page.isAddExportButtonDisplayed(),
                "Add Export button should be visible on Data Exports");
    }
}
