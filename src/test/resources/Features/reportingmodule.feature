@UITest @RegressionTest @ReportingModule
Feature: Reporting module features

  Background:
    Given report manager login
    And report manager is on the dashboard page

  @TotalShippedOrdersReport
  Scenario Outline: Reporting Manager should be able to see total shipped orders report
    Given reporting manager is on the admin page and clicks shipping report
    When reporting manager fills out report date for the shipped orders "<dateFrom>" and"<dateTo>"
    Then total shipped report should appear
    Examples:
      | dateFrom   | dateTo     |
      | 21/01/2010 | 26/12/2023 |


  @ManageProductsDownloadsReport
  Scenario: Reporting manager should be able to see products downloads reports
    Given reporting manager is on the admin dashboard page
    When reporting manager downloads the existing reports
    Then Products reports should be see able

  @Manage_Products_Reviews
  Scenario: Reporting manager should be able to see products reviews
    Given reporting manager is on the dashboard page
    When  reporting manager opens the reviews page and sees the reviews
    Then  products reviews should be see able

  @Products-low-stock-Report
  Scenario: Reporting Manager should be able to see Products low stock Report
    Given reporting manager is on the dashboard page
    When  reporting manager navigate to low stock page
    Then  products low stock report should be display

  @TotalOrderReport
  Scenario: Reporting Manager should be able to see Sales - Total Ordered Report
    Given reporting manager navigate to order report page
    When reporting manager filter total order report with date
    Then total order report should be displayed

  @ViewSalesTaxReport
  Scenario Outline: Reporting Manager Should be Able to See Sales-Taxes Report Grouped by Tax Rate
    Given reporting manager is on the Order Taxes Report Page
    When reporting manager fills the filter options with "<From>" and "<To>"
    Then reporting manager should be able to see the Sales-Taxes Report Grouped By Tax Rate
    Examples:
      | From     | To         |
      | 9/1/2010 | 12/26/2023 |

  @ViewCustomerByOrderTotal
  Scenario:Reporting Manager should be able to see Customers - Customers by Orders Total Report
    Given reporting manager is on the Customers by Orders Total page
    When reporting manager apply filters for the report
    Then reporting manager should be able to see Customers - Customers by Orders Total Report


  @ViewNewCustomerReport
  Scenario: Reporting Manager should be able to see Customers-New Accounts Report
    Given reporting manager is on the New Accounts page
    When reporting manager filter customer report and search for it
    Then reporting manager should be able to see Customers Report-New Accounts Report

    @SeeTagForProduct
    Scenario:  Reporting Manager should be able to see Tags for product
      Given reporting manager should be navigate to dashboard page
      When reporting manager see tags for product
      Then product tag should be visible on the reporting page
