@DatabaseTest @RegressionTest
Feature: eCommerce Magento Database features
  Background:
    Given user has valid database connection

@AddedSubCategories
Scenario: User retrieves sub category info from the database
  Given user has valid database connection
  When Execute SQL query to get sub category information with categoryName Mobile
  Then The database returns sub category information with details

  @Verify_newly_Added_Customer
  Scenario: user retrieves that newly registered users information
#    Given The user has valid database connection
    When Execute SQL query to get newly registered users information by email salmanuyghur3@gmail.com
    Then Database returns newly registered information

