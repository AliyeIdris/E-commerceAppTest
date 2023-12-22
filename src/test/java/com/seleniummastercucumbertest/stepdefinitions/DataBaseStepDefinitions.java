package com.seleniummastercucumbertest.stepdefinitions;

import com.seleniummastercucumber.pages.database.VerifySQLScripts;
import com.seleniummastercucumber.utility.ConnectionType;
import com.seleniummastercucumber.utility.DBConnection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.Connection;

import static com.seleniummastercucumber.utility.FileUtility.readConfig;

public class DataBaseStepDefinitions {
    Connection connection;
    VerifySQLScripts verifySQLScripts;
    DBConnection dbConnection = new DBConnection();
    String dbUrl =readConfig("dburl");
    String dbPort = readConfig("dbport");
    String username = readConfig("dbusername");
    String password = readConfig("dbpassword");
    String dbName = readConfig("dbname");
    boolean isCategoryExist ;
    boolean isCustomerExist;

    boolean isTaxRuleNameExist;


    @Given("user has valid database connection")
    public void userHasValidDatabaseConnection() {
        connection = dbConnection.connectToDataBaseServer(dbUrl, dbPort, username, password, dbName
                , ConnectionType.MYSQL);
        verifySQLScripts = new VerifySQLScripts();
    }

    @When("Execute SQL query to get sub category information with categoryName {}")
    public void executeSQLQueryToGetSubCategoryInformationWithCategoryName(String subCategoryName) {
        isCategoryExist = verifySQLScripts.getAddedSubCategoriesInfo(connection,subCategoryName);


    }

    @Then("The database returns sub category information with details")
    public void theDatabaseReturnsSubCategoryInformationWithDetails() {
        Assert.assertTrue(isCategoryExist);
    }

    @When("Execute SQL query to get newly registered users information by email")
    public void executeSQLQueryToGetNewlyRegisteredUsersInformationByEmail() {
        isCustomerExist=verifySQLScripts.newlyRegisteredUser("uyhgur999@gmail.com",connection);

    }
    @Then("Database returns newly registered information")
    public void databaseReturnsNewlyRegisteredInformation() {
      Assert.assertTrue(isCustomerExist);

    }


    @When("the user query the mg_tax_calculation_rule table with taxRuleName")
    public void theUserQueryTheMg_tax_calculation_ruleTableWithTaxRuleName() {
       // AdminLoginPage loginPage = new AdminLoginPage(BasePage.driver);
       // loginPage.login(AdminRole.SALES_MANAGER);
        isTaxRuleNameExist=VerifySQLScripts.getNewlyAddedTaxRule(connection,"Wholesale Customer - Tax Exempt ");
    }

    @Then("the user should see the newly added tax rule info")
    public void theUserShouldSeeTheNewlyAddedTaxRuleInfo() {
        Assert.assertTrue(isTaxRuleNameExist);
    }
}
