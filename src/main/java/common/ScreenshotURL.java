package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.AppiumDriver;

public final class ScreenshotURL {

    private ScreenshotURL() {
        // Utility class
    }

    public static void capture(Object driver,
                               String baseFolder,
                               String testClass,
                               String testName) {

        if (!(driver instanceof TakesScreenshot)) {
            System.err.println("Driver does not support screenshots");
            return;
        }

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String folderPath =
                baseFolder + File.separator + testClass;

        String screenshotPath =
                folderPath + File.separator + testName + "_" + timestamp + ".png";

        String metaPath =
                folderPath + File.separator + testName + "_" + timestamp + ".txt";

        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // -------- Screenshot --------
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(screenshotPath));

            // -------- Metadata --------
            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(metaPath))) {

                writer.write("Timestamp: " + timestamp);
                writer.newLine();

                if (driver instanceof WebDriver && !(driver instanceof AppiumDriver)) {
                    System.out.println(((WebDriver) driver).getCurrentUrl());
                } else if (driver instanceof AppiumDriver) {
                    AppiumDriver appium = (AppiumDriver) driver;
                    writer.write("Platform: " +
                            appium.getCapabilities().getPlatformName());
                }
            }

            System.out.println("📸 Screenshot saved: " + screenshotPath);

        } catch (Exception e) {
            System.err.println("Screenshot failed: " + e.getMessage());
        }
    }
}
