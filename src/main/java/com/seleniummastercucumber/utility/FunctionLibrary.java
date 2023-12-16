package com.seleniummastercucumber.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.seleniummastercucumber.utility.FileUtility.readConfig;

/**
 * @author : user
 * @created : 8.12.2023,16:49
 * @Email :aliyeidiris@gmail.com
 **/
public class FunctionLibrary {
    WebDriver driver;
    public FunctionLibrary(WebDriver driver) {
        this.driver = driver;
    }
    int timeout=Integer.parseInt(readConfig("timeout"));
    public void waitForElementVisible(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitAlertPresent(){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    public void javaScriptClick(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", element);
    }
    public void javaScriptScroll() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
