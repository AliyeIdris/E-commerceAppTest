@DatabaseTest @RegressionTest
Feature: eCommerce Magento Database features
  Background:
    Given user has valid database connection

@AddedSubCategories
Scenario: User retrieves sub category info from the database
  Given user has valid database connection
  When Execute SQL query to get sub category information with categoryName Mobile
  Then The database returns sub category information with details

  @NewlyAddedStoreView
  Scenario: user get newly added store view info from database
    Given user has valid database connection
    When run SQL query to get newly added store view info with store view name "Nice Product"
    Then database returns store view information details


  @Verify_newly_Added_Customer
  Scenario: user retrieves that newly registered users information
    Given  user has valid database connection
    When Execute SQL query to get newly registered users information by email
    Then Database returns newly registered information


  @VerifyAddedTaxRule
  Scenario: verify newly added tax rule should be in the database
    Given user has valid database connection
    When the user query the mg_tax_calculation_rule table with taxRuleName
    Then the user should see the newly added tax rule info

