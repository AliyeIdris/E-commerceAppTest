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
        plugin = {"pretty",
                "html:target/cucumber-html-report.html",
                "json:target/cucumber-json-report.json",
                "junit:target/cucumber-xml-report.xml" },

        features = {"classpath:Features/"},
        tags="@RegressionTest"
)
public class CucumberTest {
}
