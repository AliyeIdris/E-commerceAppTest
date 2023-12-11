package magentocucumber.universalfunctions;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static magentocucumber.universalfunctions.FileUtility.readConfig;

/**
 * @author : user
 * @created : 8.12.2023,16:54
 * @Email :aliyeidiris@gmail.com
 **/
public class ScreenShotUtility {
    public void takeScreenshot(String fileName, WebDriver driver) {
        DateTime dt1 = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd-HH-mm-ss-SSS");
        String timestamp = dt1.toString(formatter);
        fileName = fileName + "-" + timestamp;
        String folderPath=readConfig("screenshot");
        File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            org.apache.commons.io.FileUtils.copyFile(imageFile, new File(folderPath + File.separator + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
