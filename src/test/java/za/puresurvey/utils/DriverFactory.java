package za.puresurvey.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import za.puresurvey.config.ConfigReader;

/**
 * Creates and configures the WebDriver browser instance.
 * WebDriverManager downloads the correct driver for Chrome, Firefox, or Edge.
 */
public class DriverFactory {

    /**
     * Starts a new browser based on config.properties (browser=chrome).
     */
    public static WebDriver createDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            return new ChromeDriver(options);
        }

        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            return driver;
        }

        if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            WebDriver driver = new EdgeDriver();
            driver.manage().window().maximize();
            return driver;
        }

        throw new IllegalArgumentException("Unsupported browser in config.properties: " + browser);
    }
}
