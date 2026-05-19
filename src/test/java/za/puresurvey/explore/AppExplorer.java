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
 * One-time helper to explore the Pure Survey UI and print locators.
 * Run with: mvn test -Dtest=AppExplorer
 * (Add this class to testng only when exploring; it is not part of the main suite.)
 */
public class AppExplorer {

    public static void main(String[] args) throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        String outputPath = "target/live-exploration.txt";
        PrintWriter out = new PrintWriter(new FileWriter(outputPath));

        try {
            driver.get("https://survey.co.za/app/login");
            out.println("=== LOGIN PAGE ===");
            out.println("URL: " + driver.getCurrentUrl());
            out.println("Title: " + driver.getTitle());
            printInputs(driver, out);
            printButtons(driver, out);

            // Login
            List<WebElement> emailFields = driver.findElements(By.cssSelector("input[type='email'], input[name='email'], input[placeholder*='mail' i]"));
            List<WebElement> passwordFields = driver.findElements(By.cssSelector("input[type='password']"));
            if (!emailFields.isEmpty()) {
                emailFields.get(0).sendKeys("prudance+10@puresurvey.co.za");
            } else {
                driver.findElement(By.xpath("//input[contains(@type,'text') or contains(@placeholder,'mail')]")).sendKeys("prudance+10@puresurvey.co.za");
            }
            passwordFields.get(0).sendKeys("Manager@123");
            WebElement loginBtn = driver.findElement(By.xpath("//button[contains(.,'Sign') or contains(.,'Log') or @type='submit']"));
            loginBtn.click();

            wait.until(ExpectedConditions.urlContains("/app"));
            Thread.sleep(3000);
            out.println("\n=== AFTER LOGIN ===");
            out.println("URL: " + driver.getCurrentUrl());
            printButtons(driver, out);
            printLinks(driver, out);
            printH1(driver, out);

            dismissOnboarding(driver);

            // Dashboard
            out.println("\n=== DASHBOARD ===");
            printH1(driver, out);
            printSideNav(driver, out);

            dismissOnboarding(driver);

            // Surveys - use LEFT side nav link (top "Surveys" button does not change page)
            clickSideLink(driver, wait, "Surveys");
            Thread.sleep(2000);
            out.println("\n=== SURVEYS LIST (side nav) ===");
            out.println("URL: " + driver.getCurrentUrl());
            printH1(driver, out);
            printButtons(driver, out);
            printTableHeaders(driver, out);

            // Question Bank
            clickSideLink(driver, wait, "Question Bank");
            Thread.sleep(1500);
            out.println("\n=== QUESTION BANK ===");
            out.println("URL: " + driver.getCurrentUrl());
            printH1(driver, out);

            // Translations
            clickSideLink(driver, wait, "Translations");
            Thread.sleep(1500);
            out.println("\n=== TRANSLATIONS ===");
            out.println("URL: " + driver.getCurrentUrl());
            printH1(driver, out);

            // Insight Reports
            clickSideLink(driver, wait, "Insight Reports");
            Thread.sleep(1500);
            out.println("\n=== INSIGHT REPORTS ===");
            out.println("URL: " + driver.getCurrentUrl());
            printH1(driver, out);

            // Distribution - top nav button opens sub-menu / section
            clickTopNavButton(driver, wait, "Distribution");
            Thread.sleep(2000);
            out.println("\n=== DISTRIBUTION MENU ===");
            printLinks(driver, out);

            clickLink(driver, wait, "Distribution Lists");
            Thread.sleep(1500);
            out.println("\n=== DISTRIBUTION LISTS ===");
            out.println("URL: " + driver.getCurrentUrl());
            printH1(driver, out);

            clickLink(driver, wait, "Panels");
            Thread.sleep(1500);
            out.println("\n=== PANELS ===");
            out.println("URL: " + driver.getCurrentUrl());
            printH1(driver, out);

            clickLink(driver, wait, "Email Templates");
            Thread.sleep(1500);
            out.println("\n=== EMAIL TEMPLATES ===");
            out.println("URL: " + driver.getCurrentUrl());
            printH1(driver, out);

            clickLink(driver, wait, "WhatsApp Templates");
            Thread.sleep(1500);
            out.println("\n=== WHATSAPP TEMPLATES ===");
            out.println("URL: " + driver.getCurrentUrl());
            printH1(driver, out);

            out.println("\nDONE");
            System.out.println("Exploration saved to " + outputPath);

        } finally {
            out.close();
            driver.quit();
        }
    }

    private static void dismissOnboarding(WebDriver driver) throws InterruptedException {
        List<WebElement> gotIt = driver.findElements(By.xpath("//button[contains(.,'Got it')]"));
        if (!gotIt.isEmpty() && gotIt.get(0).isDisplayed()) {
            gotIt.get(0).click();
            Thread.sleep(1000);
        }
    }

    private static void clickTopNavButton(WebDriver driver, WebDriverWait wait, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='" + text + "']")));
        el.click();
    }

    private static void clickSideLink(WebDriver driver, WebDriverWait wait, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//nav//a[normalize-space()='" + text + "']")));
        el.click();
    }

    private static void clickLink(WebDriver driver, WebDriverWait wait, String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(normalize-space(),'" + text + "')]")));
        el.click();
    }

    private static void printH1(WebDriver driver, PrintWriter out) {
        List<WebElement> h1s = driver.findElements(By.tagName("h1"));
        for (WebElement h1 : h1s) {
            if (h1.isDisplayed()) {
                out.println("H1: " + h1.getText());
            }
        }
    }

    private static void printButtons(WebDriver driver, PrintWriter out) {
        out.println("Buttons:");
        for (WebElement b : driver.findElements(By.tagName("button"))) {
            String t = b.getText().trim();
            if (!t.isEmpty() && b.isDisplayed()) {
                out.println("  - " + t.replace("\n", " "));
            }
        }
    }

    private static void printLinks(WebDriver driver, PrintWriter out) {
        out.println("Links:");
        for (WebElement a : driver.findElements(By.tagName("a"))) {
            String t = a.getText().trim();
            if (!t.isEmpty() && a.isDisplayed()) {
                out.println("  - " + t.replace("\n", " "));
            }
        }
    }

    private static void printInputs(WebDriver driver, PrintWriter out) {
        out.println("Inputs:");
        for (WebElement i : driver.findElements(By.tagName("input"))) {
            out.println("  - type=" + i.getAttribute("type")
                    + " placeholder=" + i.getAttribute("placeholder")
                    + " name=" + i.getAttribute("name"));
        }
    }

    private static void printTableHeaders(WebDriver driver, PrintWriter out) {
        out.println("Table headers (th):");
        for (WebElement th : driver.findElements(By.tagName("th"))) {
            String t = th.getText().trim();
            if (!t.isEmpty()) {
                out.println("  - " + t);
            }
        }
    }

    private static void printSideNav(WebDriver driver, PrintWriter out) {
        out.println("Side nav links:");
        for (WebElement a : driver.findElements(By.xpath("//nav//a"))) {
            String t = a.getText().trim();
            if (!t.isEmpty()) {
                out.println("  - " + t);
            }
        }
    }
}
