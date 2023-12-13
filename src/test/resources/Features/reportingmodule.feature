@UITest @RegressionTest @ReportingModule
Feature: Reporting module features

  @TotalShippedOrdersReport
  Scenario Outline: Reporting Manager should be able to see total shipped orders report
    Given reporting manager is on the admin page and clicks shipping report
    When reporting manager fills out report date for the shipped orders "<dateFrom>" and"<dateTo>"
    Then total shipped report should appear
    Examples:
      | dateFrom   | dateTo     |
      | 03/21/2023 | 12/12/2023 |