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
import za.puresurvey.pages.surveys.InsightsReportPage;
import za.puresurvey.pages.surveys.QuestionBankPage;
import za.puresurvey.pages.surveys.TranslationsPage;

/**
 * Tests for Surveys sub-modules: Question Bank, Translations, Insight Reports.
 */
@Epic("Pure Survey")
@Feature("Surveys Sub-modules")
public class SurveysSubModulesTest extends BaseTest {

    /**
     * Log in before each test.
     */
    @BeforeMethod(alwaysRun = true)
    public void loginToApp() {
        DashboardPage dashboard = new LoginPage(driver).open().loginWithConfigCredentials();
        dashboard.waitForPageToLoad();
    }

    /**
     * S5: Question Bank page loads.
     */
    @Test(description = "Question Bank page loads")
    @Description("Navigate to Question Bank via side nav")
    public void questionBankPageLoads() {
        QuestionBankPage page = new QuestionBankPage(driver).openFromSideNav();
        Assert.assertEquals(page.getPageHeadingText(), "Question Bank");
        Assert.assertTrue(page.isSearchFieldDisplayed());
        Assert.assertTrue(page.isAddQuestionButtonDisplayed());
    }

    /**
     * S6: Translations page loads.
     */
    @Test(description = "Translations page loads")
    @Description("Navigate to Survey Translations via side nav")
    public void translationsPageLoads() {
        TranslationsPage page = new TranslationsPage(driver).openFromSideNav();
        Assert.assertEquals(page.getPageHeadingText(), "Survey Translations");
    }

    /**
     * S7: Insight Reports page loads.
     */
    @Test(description = "Insight Reports page loads")
    @Description("Navigate to Insight Reports via side nav")
    public void insightReportsPageLoads() {
        InsightsReportPage page = new InsightsReportPage(driver).openFromSideNav();
        Assert.assertEquals(page.getPageHeadingText(), "Insight Reports");
    }
}
