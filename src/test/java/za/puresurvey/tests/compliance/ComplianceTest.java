package za.puresurvey.tests.compliance;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.puresurvey.base.BaseTest;
import za.puresurvey.pages.compliance.AccessLogsPage;
import za.puresurvey.pages.compliance.AuditLogPage;
import za.puresurvey.pages.compliance.ConsentRecordsPage;
import za.puresurvey.pages.compliance.OptOutManagementPage;
import za.puresurvey.pages.dashboard.DashboardPage;
import za.puresurvey.pages.login.LoginPage;

/**
 * Smoke tests for the Compliance module.
 */
@Epic("Pure Survey")
@Feature("Compliance")
public class ComplianceTest extends BaseTest {

    /**
     * Log in before each test.
     */
    @BeforeMethod(alwaysRun = true)
    public void loginToApp() {
        DashboardPage dashboard = new LoginPage(driver).open().loginWithConfigCredentials();
        dashboard.waitForPageToLoad();
    }

    /**
     * Consent Records page loads (heading shown as Consent Management in the app).
     */
    @Test(description = "Consent Records page loads")
    @Description("Open Consent Records from Compliance menu")
    public void consentRecordsPageLoads() {
        ConsentRecordsPage page = new ConsentRecordsPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Consent Management",
                "Consent Records screen heading is 'Consent Management' in the UI");
        Assert.assertTrue(page.getCurrentUrl().contains("/consent"));
        Assert.assertTrue(page.isExportCsvButtonDisplayed(),
                "Export CSV button should be visible on Consent Records");
    }

    /**
     * Opt-out Management page loads.
     */
    @Test(description = "Opt-out Management page loads")
    @Description("Open Opt-out Management from Compliance menu")
    public void optOutManagementPageLoads() {
        OptOutManagementPage page = new OptOutManagementPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Opt-out Management");
        Assert.assertTrue(page.getCurrentUrl().contains("/opt-outs"));
    }

    /**
     * Audit Log page loads.
     */
    @Test(description = "Audit Log page loads")
    @Description("Open Audit Log from Compliance menu")
    public void auditLogPageLoads() {
        AuditLogPage page = new AuditLogPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Audit Log");
        Assert.assertTrue(page.getCurrentUrl().contains("/audit-log"));
    }

    /**
     * Access Logs page loads.
     */
    @Test(description = "Access Logs page loads")
    @Description("Open Access Logs from Compliance menu")
    public void accessLogsPageLoads() {
        AccessLogsPage page = new AccessLogsPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Access Logs");
        Assert.assertTrue(page.getCurrentUrl().contains("/access-logs"));
    }
}
