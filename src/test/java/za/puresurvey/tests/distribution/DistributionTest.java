package za.puresurvey.tests.distribution;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.puresurvey.base.BaseTest;
import za.puresurvey.pages.dashboard.DashboardPage;
import za.puresurvey.pages.distribution.DistributionListPage;
import za.puresurvey.pages.distribution.EmailTemplatePage;
import za.puresurvey.pages.distribution.PanelsPage;
import za.puresurvey.pages.distribution.WhatsAppTemplatePage;
import za.puresurvey.pages.login.LoginPage;

/**
 * Tests for the Distribution module (lists, panels, templates).
 */
@Epic("Pure Survey")
@Feature("Distribution")
public class DistributionTest extends BaseTest {

    /**
     * Log in before each test.
     */
    @BeforeMethod(alwaysRun = true)
    public void loginToApp() {
        DashboardPage dashboard = new LoginPage(driver).open().loginWithConfigCredentials();
        dashboard.waitForPageToLoad();
    }

    /**
     * DI1: Distribution Lists page loads.
     */
    @Test(description = "Distribution Lists page loads")
    @Description("Open Distribution Lists from top nav menu")
    public void distributionListsPageLoads() {
        DistributionListPage page = new DistributionListPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Distribution Lists");
        Assert.assertTrue(page.getCurrentUrl().contains("/lists"));
    }

    /**
     * DI2: Panels page loads.
     */
    @Test(description = "Panels page loads")
    @Description("Open Panels from Distribution menu")
    public void panelsPageLoads() {
        PanelsPage page = new PanelsPage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Panels");
        Assert.assertTrue(page.getCurrentUrl().contains("/panels"));
    }

    /**
     * DI3: Email Templates page loads.
     */
    @Test(description = "Email Templates page loads")
    @Description("Open Email Templates from Distribution menu")
    public void emailTemplatesPageLoads() {
        EmailTemplatePage page = new EmailTemplatePage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "Email Templates");
        Assert.assertTrue(page.getCurrentUrl().contains("/email-templates"));
    }

    /**
     * DI4: WhatsApp Templates page loads.
     */
    @Test(description = "WhatsApp Templates page loads")
    @Description("Open WhatsApp Templates from Distribution menu")
    public void whatsAppTemplatesPageLoads() {
        WhatsAppTemplatePage page = new WhatsAppTemplatePage(driver).openFromMenu();
        Assert.assertEquals(page.getPageHeadingText(), "WhatsApp Templates");
        Assert.assertTrue(page.getCurrentUrl().contains("/whatsapp-templates"));
    }
}
