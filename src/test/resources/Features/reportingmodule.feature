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
      | 03/21/2023 | 12/12/2023 |


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

   @TotalOrderReport  #Safiya
   Scenario: Reporting Manager should be able to see Sales - Total Ordered Report
     Given reporting manager navigate to order report page
     When reporting manager filter total order report with date
     Then total order report should be displayed