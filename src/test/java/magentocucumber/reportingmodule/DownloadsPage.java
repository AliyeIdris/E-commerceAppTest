package magentocucumber.reportingmodule;

import magentocucumber.universalfunctions.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/**
 * @author : SalmanUyghur
 * @created : 12/13/2023,12:01 PM
 * @Email : salmanuyghur3@gmail.com
 **/
public class DownloadsPage {
    WebDriver driver;
    FunctionLibrary functionLibrary;
    Actions actions;

    public DownloadsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        functionLibrary=new FunctionLibrary(driver);
        actions=new Actions(driver);
    }
}
