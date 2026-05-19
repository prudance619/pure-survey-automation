package za.puresurvey.tests.dashboard;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.puresurvey.base.BaseTest;
import za.puresurvey.pages.dashboard.DashboardPage;
import za.puresurvey.pages.login.LoginPage;
import za.puresurvey.pages.surveys.QuestionBankPage;
import za.puresurvey.pages.surveys.SurveysPage;

/**
 * Tests for the Dashboard module.
 */
@Epic("Pure Survey")
@Feature("Dashboard")
public class DashboardTest extends BaseTest {

    private DashboardPage dashboard;

    /**
     * Log in once before each test in this class.
     */
    @BeforeMethod(alwaysRun = true)
    public void loginToApp() {
        dashboard = new LoginPage(driver).open().loginWithConfigCredentials();
        dashboard.waitForPageToLoad();
    }

    /**
     * D1: Dashboard loads with greeting after login.
     */
    @Test(description = "Dashboard shows greeting after login")
    @Description("Verify dashboard URL and greeting text")
    public void dashboardShowsGreeting() {
        Assert.assertTrue(dashboard.getCurrentUrl().contains("/app"),
                "User should be on dashboard URL");
        Assert.assertTrue(dashboard.isGreetingVisible(),
                "Greeting should start with 'Good'");
    }

    /**
     * D2: Key dashboard actions are visible.
     */
    @Test(description = "New Survey and Filters buttons are visible")
    @Description("Smoke check for main dashboard action buttons")
    public void dashboardActionButtonsVisible() {
        Assert.assertTrue(dashboard.isNewSurveyButtonDisplayed(),
                "New Survey button should be visible on dashboard");
        Assert.assertTrue(dashboard.isFiltersButtonDisplayed(),
                "Filters button should be visible on dashboard");
    }

    /**
     * D3: Side navigation opens Surveys list.
     */
    @Test(description = "Side nav opens Surveys list")
    @Description("Click Surveys in left nav — surveys list page opens")
    public void sideNavOpensSurveysList() {
        SurveysPage surveysPage = dashboard.goToSurveysViaSideNav();
        Assert.assertEquals(surveysPage.getPageHeadingText(), "Surveys",
                "Surveys page heading should be 'Surveys'");
    }

    /**
     * D3b: Side navigation opens Question Bank.
     */
    @Test(description = "Side nav opens Question Bank")
    @Description("Click Question Bank in left nav")
    public void sideNavOpensQuestionBank() {
        QuestionBankPage questionBank = new QuestionBankPage(driver).openFromSideNav();
        Assert.assertEquals(questionBank.getPageHeadingText(), "Question Bank",
                "Question Bank heading should match");
    }
}
