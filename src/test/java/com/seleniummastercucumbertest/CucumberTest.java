package com.seleniummastercucumbertest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author : user
 * @created : 8.12.2023,17:02
 * @Email :aliyeidiris@gmail.com
 **/

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber-testReport.html", "junit:target/cucumber-results.xml" },

        features = {"classpath:features/reportingmodule.feature"}
        //tags = "@SmokeTest "
        //tags="@viewCouponsReports"
       // tags="@TotalShippedOrdersReport"
       // tags = "@ManageInvoicesAndComment"
        // tags="@Manage_Products_Reviews"


)
public class CucumberTest {
}
