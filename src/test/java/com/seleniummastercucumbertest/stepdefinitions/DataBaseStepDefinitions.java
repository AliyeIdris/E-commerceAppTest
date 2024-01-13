package com.seleniummastercucumbertest.stepdefinitions;

import com.seleniummastercucumber.pages.database.VerifySQLScripts;
import com.seleniummastercucumber.utility.ConnectionType;
import com.seleniummastercucumber.utility.DBConnection;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.SQLException;

import static com.seleniummastercucumber.utility.FileUtility.readConfig;

public class DataBaseStepDefinitions {
    private Scenario scenario;
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

    boolean isStoreViewExist;
    boolean isCustomerAdded;
    boolean isCartRuleAdded;
    boolean isRootCategoryExist;
    boolean isNewlyAddedStockExist;

    @Before
    public void beforeDatabaseTest(Scenario scenario){
        this.scenario=scenario;
    }

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

    @When("run SQL query to get newly added store view info with store view name {string}")
    public void runSQLQueryToGetNewlyAddedStoreViewInfoWithStoreViewName(String name) {
        isStoreViewExist=verifySQLScripts.getNewlyAddedStoreView(connection,name);
    }

    @Then("database returns store view information details")
    public void databaseReturnsStoreViewInformationDetails() {
        Assert.assertTrue("Store view is not exist ",isStoreViewExist);
    }

    //*************************** Verify Newly Added Customer ***************************
    @Given("the user has read access to the mg_customer_entity table")
    public void theUserHasReadAccessToTheMg_customer_entityTable() {
        scenario.log("User has valid username: "+username);
        scenario.log("User has valid password: "+password);
    }

    @When("user query to get the customer info with email {string}")
    public void userQueryToGetTheCustomerInfoWithEmail(String email) {
        isCustomerAdded= verifySQLScripts.getCustomerInfo(connection,email);
    }

    @Then("database should return the newly added customer with detailed info")
    public void databaseShouldReturnTheNewlyAddedCustomerWithDetailedInfo() {

        Assert.assertTrue(isCustomerAdded);
    }

    @Given("user has read access to the mg_salesrule table")
    public void  userHasReadAccessToTheMg_salesruleTable() {
    scenario.log("User has valid username: "+username);
    scenario.log("User has valid password: "+password);
    }

    @When("user execute query to get the added rule with {string}")
    public void userExecuteQueryToGetTheAddedRuleWith(String id) {
    isCartRuleAdded=verifySQLScripts.getNewlyAddedCartRule(connection,id);
}

    @Then("database should return the newly added rule with expected information")
    public void databaseShouldReturnTheNewlyAddedRuleWithExpectedInfo() {

        Assert.assertTrue(isCartRuleAdded);
    }
//abdugeni//
    @Given("user has valid database connection and ready to test")
    public void userHasValidDatabaseConnectionAndReadyToTest() throws SQLException {
        scenario.log("DataBase ready to test , Database position is : \n----->   "+
                connection.getAutoCommit());
    }
    @When("Execute SQL query to get root category information with categoryName {string}")
    public void executeSQLQueryToGetRootCategoryInformationWithCategoryName(String arg0) {
        isRootCategoryExist=verifySQLScripts.getAddedRootCategoriesInfo(connection,arg0);
    }
    @Then("The database returns root category information with details")
    public void theDatabaseReturnsRootCategoryInformationWithDetails() {
        Assert.assertTrue(isRootCategoryExist);
    }

    @After
    public void closeConnection(){
        dbConnection.closeDataBaseConnection(connection);
        scenario.log("Connection is closed!");
    }


    @Given("User has valid database connection and ready to test")
    public void UserHasValidDatabaseConnectionAndReadyToTest() {
        connection = dbConnection.connectToDataBaseServer(dbUrl, dbPort, username, password, dbName
                , ConnectionType.MYSQL);
        verifySQLScripts = new VerifySQLScripts();
    }

    @When("Execute SQL query to get stock information with stock id {string}")
    public void executeSQLQueryToGetStockInformationWith(String stockId) {
     isNewlyAddedStockExist= verifySQLScripts.VerifyNewlyAddedStock(connection,stockId);
    }
    @Then("The database returns stock information with details")
    public void theDatabaseReturnsStockInformationWithDetails() {
     Assert.assertTrue(isNewlyAddedStockExist);
    }



}
