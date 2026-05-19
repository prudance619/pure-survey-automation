package za.puresurvey.tests.surveys;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.puresurvey.base.BaseTest;
import za.puresurvey.pages.dashboard.DashboardPage;
import za.puresurvey.pages.login.LoginPage;
import za.puresurvey.pages.surveys.SurveysPage;

/**
 * Tests for the Surveys list page.
 */
@Epic("Pure Survey")
@Feature("Surveys")
public class SurveysTest extends BaseTest {

    private SurveysPage surveysPage;

    /**
     * Log in and open Surveys list before each test.
     */
    @BeforeMethod(alwaysRun = true)
    public void openSurveysPage() {
        DashboardPage dashboard = new LoginPage(driver).open().loginWithConfigCredentials();
        dashboard.waitForPageToLoad();
        surveysPage = dashboard.goToSurveysViaSideNav().waitForPageToLoad();
    }

    /**
     * S1: Surveys list shows heading and table headers.
     */
    @Test(description = "Surveys list loads with table headers")
    @Description("Verify Surveys heading and main table columns")
    public void surveysListShowsTableHeaders() {
        Assert.assertEquals(surveysPage.getPageHeadingText(), "Surveys");
        Assert.assertTrue(surveysPage.isTableHeaderVisible("Title"),
                "Title column should be visible");
        Assert.assertTrue(surveysPage.isTableHeaderVisible("Status"),
                "Status column should be visible");
        Assert.assertTrue(surveysPage.isSearchFieldDisplayed(),
                "Search surveys field should be visible");
    }

    /**
     * S2: Status filter tabs can be clicked (smoke — does not verify row data).
     * TODO: Add assertion on visible row status once test data strategy is defined.
     */
    @Test(description = "Status filter tabs are clickable")
    @Description("Click Draft and Active filters without error")
    public void statusFilterTabsClickable() {
        surveysPage.clickStatusFilter("Draft");
        surveysPage.clickStatusFilter("Active");
        surveysPage.clickStatusFilter("All");
        Assert.assertEquals(surveysPage.getPageHeadingText(), "Surveys",
                "Page should remain on Surveys after filtering");
    }

    /**
     * S3: Search field accepts input.
     */
    @Test(description = "Survey search field accepts text")
    @Description("Type in search box — page stays on Surveys list")
    public void searchFieldAcceptsText() {
        surveysPage.searchSurveys("Employee");
        Assert.assertTrue(surveysPage.isSearchFieldDisplayed());
        Assert.assertEquals(surveysPage.getPageHeadingText(), "Surveys");
    }

    /**
     * S4: Create Survey panel opens.
     */
    @Test(description = "Create Survey opens options panel")
    @Description("Click Create Survey — Start from Scratch option should appear")
    public void createSurveyPanelOpens() {
        surveysPage.clickCreateSurvey();
        Assert.assertTrue(surveysPage.isCreatePanelVisible(),
                "Create Survey panel should show 'Start from Scratch'");
    }
}
