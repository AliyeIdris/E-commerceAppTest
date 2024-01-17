@DatabaseTest @RegressionTest
Feature: eCommerce Magento Database features

  Background:
    Given user has valid database connection

  @AddedSubCategories     #Xamxinur.A
  Scenario: User retrieves sub category info from the database
    When user query to get sub category info with categoryName Shoes
    Then database should return the newly added sub category with detailed info

  @NewlyAddedStoreView
  Scenario: user get newly added store view info from database
    When run SQL query to get newly added store view info with store view name "Nice Product"
    Then database returns store view information details

  @verifyNewlyRegisteredUser
  Scenario: user retrieves that newly registered users information
    When Execute SQL query to get newly registered users information by email
    Then Database returns newly registered information

  @VerifyAddedTaxRule
  Scenario: verify newly added tax rule should be in the database
    When the user query the mg_tax_calculation_rule table with taxRuleName
    Then the user should see the newly added tax rule info

  @VerifyNewlyAddedCustomer
  Scenario: Verify that newly added customers should be in the database
    Given the user has read access to the mg_customer_entity table
    When user query to get the customer info with email "aliyeidiris@gmail.com"
    Then database should return the newly added customer with detailed info

  @VerifyAddedCartRule
  Scenario: verify newly added cart rule should be in the database
    Given user has read access to the mg_salesrule table
    When user execute query to get the added rule with "71"
    Then database should return the newly added rule with expected information

  @AddedRootCategory
  Scenario: newly added product root category should be in the database
    Given user has valid database connection and ready to test
    When Execute SQL query to get root category information with categoryName "Team1-Istanbul-DO Not Delete"
    Then The database returns root category information with details

    @VerifyNewlyAddedStock
    Scenario:Verify that newly added stock should be in the database
      Given User has valid database connection and ready to test
      When Execute SQL query to get stock information with stock id "231"
      Then The database returns stock information with details

  @DatabaseAddNewCreditMemos
  Scenario: Verify newly added Credit Memo in database
    When execute SQl query to get the new Credit Memo  information from the database
    Then  the database should contain the newly addedCreditMemo

