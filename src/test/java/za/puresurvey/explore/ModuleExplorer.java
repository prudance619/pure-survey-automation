package za.puresurvey.explore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;

/**
 * Explores Compliance, Integrations, and Administration menus.
 * Run: mvn test-compile exec:java "-Dexec.mainClass=za.puresurvey.explore.ModuleExplorer" "-Dexec.classpathScope=test"
 */
public class ModuleExplorer {

    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        String outputPath = "target/module-exploration.txt";
        PrintWriter out = new PrintWriter(new FileWriter(outputPath));

        try {
            driver.get("https://survey.co.za/app/login");
            login(driver, wait);

            dismissGotIt(driver);
            exploreTopMenu(driver, wait, out, "Compliance");
            exploreTopMenu(driver, wait, out, "Integrations");
            exploreTopMenu(driver, wait, out, "Administration");

            out.println("\nDONE");
            System.out.println("Saved to " + outputPath);
        } finally {
            out.close();
            driver.quit();
        }
    }

    private static void login(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("prudance+10@puresurvey.co.za");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Manager@123");
        driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();
        wait.until(d -> d.getCurrentUrl().contains("/app") && !d.getCurrentUrl().contains("login"));
        Thread.sleep(2000);
        dismissGotIt(driver);
    }

    private static void dismissGotIt(WebDriver driver) throws InterruptedException {
        List<WebElement> gotIt = driver.findElements(By.xpath("//button[contains(.,'Got it')]"));
        if (!gotIt.isEmpty() && gotIt.get(0).isDisplayed()) {
            gotIt.get(0).click();
            Thread.sleep(1000);
        }
    }

    private static void exploreTopMenu(WebDriver driver, WebDriverWait wait, PrintWriter out, String menuName)
            throws InterruptedException {
        out.println("\n=== TOP MENU: " + menuName.toUpperCase() + " ===");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='" + menuName + "']")));
        btn.click();
        Thread.sleep(2000);

        out.println("Sub-links after clicking " + menuName + ":");
        for (WebElement a : driver.findElements(By.tagName("a"))) {
            String t = a.getText().trim().replace("\n", " ");
            if (!t.isEmpty() && a.isDisplayed()) {
                out.println("  - " + t + " | href=" + a.getAttribute("href"));
            }
        }

        // Click each likely sub-link and record URL + H1
        String[] keywords = getKeywordsForMenu(menuName);
        for (String keyword : keywords) {
            try {
                WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(normalize-space(),'" + keyword + "')]")));
                link.click();
                Thread.sleep(2000);
                dismissGotIt(driver);
                out.println("\n--- " + keyword + " ---");
                out.println("URL: " + driver.getCurrentUrl());
                printH1(driver, out);
                printButtons(driver, out);

                // Re-open menu for next link
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='" + menuName + "']"))).click();
                Thread.sleep(1000);
            } catch (Exception e) {
                out.println("\n--- " + keyword + " --- FAILED: " + e.getMessage());
                driver.navigate().refresh();
                Thread.sleep(2000);
                dismissGotIt(driver);
            }
        }
    }

    private static String[] getKeywordsForMenu(String menuName) {
        if (menuName.equals("Compliance")) {
            return new String[]{"Consent Records", "Opt-out", "Opt-out Management", "Audit Log", "Access Log", "Access Logs"};
        }
        if (menuName.equals("Integrations")) {
            return new String[]{"Automations", "Webhooks", "Data Export", "Data Exports"};
        }
        return new String[]{"Users", "Org Units", "Org Unit", "Settings"};
    }

    private static void printH1(WebDriver driver, PrintWriter out) {
        for (WebElement h1 : driver.findElements(By.tagName("h1"))) {
            if (h1.isDisplayed()) {
                out.println("H1: " + h1.getText());
            }
        }
    }

    private static void printButtons(WebDriver driver, PrintWriter out) {
        out.println("Visible buttons (sample):");
        int count = 0;
        for (WebElement b : driver.findElements(By.tagName("button"))) {
            String t = b.getText().trim().replace("\n", " ");
            if (!t.isEmpty() && b.isDisplayed() && count < 15) {
                out.println("  - " + t);
                count++;
            }
        }
    }
}
