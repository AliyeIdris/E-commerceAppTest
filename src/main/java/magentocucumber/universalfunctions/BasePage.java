package magentocucumber.universalfunctions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static magentocucumber.universalfunctions.FileUtility.readConfig;

/**
 * @author : user
 * @created : 8.12.2023,16:39
 * @Email :aliyeidiris@gmail.com
 **/
public class BasePage {
    public static WebDriver driver;
    static boolean headlessMode=Boolean.parseBoolean(readConfig("headlessMode"));
    public static void setUp(DriverType driverType,String url){
        switch (driverType) {
            case CHROME_DRIVER -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_WINDOWS) {
                    if (headlessMode) {
                        chromeOptions.addArguments("headless");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
                    driver.get(url);
                }
                if (SystemUtils.IS_OS_LINUX) {
                    chromeOptions.addArguments("headless");
                    chromeOptions.addArguments("window-size=1200,1100");
                    chromeOptions.addArguments("disable-gpu");
                    driver = new ChromeDriver(chromeOptions);
                    driver.get(url);
                }
            }
            case FIREFOX_DRIVER -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_WINDOWS) {
                    if (headlessMode) {
                        firefoxOptions.addArguments("headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    driver.manage().window().maximize();
                    driver.get(url);
                }
                if (SystemUtils.IS_OS_LINUX) {
                    firefoxOptions.addArguments("headless");
                    firefoxOptions.addArguments("window-size=1200,1100");
                    firefoxOptions.addArguments("disable-gpu");
                    driver = new FirefoxDriver(firefoxOptions);
                    driver.get(url);
                }
            }
            case SAFARI_DRIVER -> {
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new SafariDriver(safariOptions);
                driver.manage().window().maximize();
                driver.get(url);
            }
        }
    }
    public static void closeBrowser(){
        driver.close();
        driver.quit();
    }
}
