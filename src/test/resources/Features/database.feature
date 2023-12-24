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

