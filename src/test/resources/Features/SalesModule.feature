@UITest @RegressionTest @SalesModule
Feature: Sales module features

  Background:
    Given sales manager login
    And sales manager on the dashboard page

    @ManageShoppingCart
    Scenario: Sales Manager should be able to manage view shopping cart for customers.
      description: (You need to open a customer and manage shopping cart) -Aliye
      Given sales manager is on customer's shopping cart page "aliyeidiris@gmail.com"
      When sales manager empty Customer's Shopping Cart
      Then sales manager managed shopping cart successfully

      @ManageInvoicesAndComment
      Scenario: Sales manager should be able to view invoices and add comments
        Given Sales manager is on the dashboard page and invoices are should be existing
        When Sales manager views invoices and adds comments
        Then Sales manager should be able to view invoices and add comments successfully



  @viewCouponsReports
  Scenario Outline:Sales Manager should be able to view coupons in the Reports
    Given sales manager is on the reports page
    When Sales manager Click on Reports move to reports and move to Sales and select coupons fill in "<fromDate>"and"<toDate>"field
    Then Total coupons Report page should display with information

    Examples:
      | fromDate  | toDate    |
      | 10/20/2023 | 12/12/2023 |

    @viewRefundsReports
    Scenario: Sales Manager should be able to view refunds in the Reports
      Given sales manager is on the refunds page
      When sales manager select on from and to date fill and click on show report
      Then total refunded report should be display with information