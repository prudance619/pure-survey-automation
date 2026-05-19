package za.puresurvey.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Saves a PNG screenshot when a test fails (or any time you call capture).
 */
public class ScreenshotHelper {

    private static final String FOLDER = "screenshots";

    /**
     * Takes a screenshot and saves it under the screenshots/ folder.
     *
     * @param driver   active WebDriver
     * @param testName name used in the file name (e.g. test method name)
     * @return full path to the saved file
     */
    public static String capture(WebDriver driver, String testName) {
        try {
            File folder = new File(FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = FOLDER + File.separator + testName + "_" + timeStamp + ".png";

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(fileName);
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return dest.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Could not save screenshot: " + e.getMessage());
            return "";
        }
    }
}
