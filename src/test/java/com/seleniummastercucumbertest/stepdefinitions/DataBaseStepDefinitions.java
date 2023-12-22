package com.seleniummastercucumbertest.stepdefinitions;

import com.seleniummastercucumber.pages.database.VerifyNewCustomer;
import com.seleniummastercucumber.pages.database.VerifySQLScripts;
import com.seleniummastercucumber.utility.ConnectionType;
import com.seleniummastercucumber.utility.DBConnection;
import com.seleniummastercucumber.utility.FileUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.Connection;

import static com.seleniummastercucumber.utility.FileUtility.readConfig;

public class DataBaseStepDefinitions {
    Connection connection;
    VerifySQLScripts verifySQLScripts;
    VerifyNewCustomer verifyNewCustomer;
    DBConnection dbConnection = new DBConnection();
    String dbUrl =readConfig("dburl");
    String dbPort = readConfig("dbport");
    String username = readConfig("dbusername");
    String password = readConfig("dbpassword");
    String dbName = readConfig("dbname");
    boolean isCategoryExist ;
    boolean isCustomerExist;

    @Given("user has valid database connection")
    public void userHasValidDatabaseConnection() {
        connection = dbConnection.connectToDataBaseServer(dbUrl, dbPort, username, password, dbName
                , ConnectionType.MYSQL);
        verifySQLScripts = new VerifySQLScripts();
        verifyNewCustomer=new VerifyNewCustomer();
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
        verifyNewCustomer.newlyRegisteredUser("salmanuyghur3@gmail.com",connection);
    }
    @Then("Database returns newly registered information")
    public void databaseReturnsNewlyRegisteredInformation() {
       // Assert.assertTrue(isCustomerExist);

    }



}
