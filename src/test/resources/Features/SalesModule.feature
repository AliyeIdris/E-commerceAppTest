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
      | fromDate   | toDate     |
      | 10/20/2023 | 12/12/2023 |

  @viewRefundsReports     #Xamxinur.A
  Scenario: Sales Manager should be able to view refunds in the Reports
    Given sales manager is on the refunds page
    When sales manager select on from and to date field and click on show report
    Then total refunded report should be displayed with information

  @UpdateShoppingCart   #Safiya
  Scenario: Sales Manager should be able to manage update an existing shopping cart for customers.
  (You need to open a customer and manage shopping cart)
    Given sales manager go to the shopping cart page with email "zubi@gmail.com"
    When sales manager update the existing shopping cart
    Then updated shopping cart info should be displayed

  @ManageTaxRules
  Scenario: Sales Manager should be able to manage (add and update) tax rules
  description: (see Tax link under Store)
    Given sales manager is on Manage Tax Rules page
    When sales manager adds tax rule
    And sales manager updates added tax rule
    Then tax rule should be added and updated successfully

  @updateShipments  #abdugeni
  Scenario: Sales Manager Should be Able To Update (
  Add Shipment History And Tracking Information) Shipments
    Given sales manager on the shipments page
    When sales manager can view shipmentsList and random select one for update
    And sales manager update shipments and send tracking information
    Then sales manager sent tracking information and info should be displayed

  @ViewCreditMemos
  Scenario: Sales Manager should be able to view credit memos
    Given sales manager is on the credit memos page
    When click on the reset filter and views tab
    Then Sales manager should be able to view credit memos successfully

  @viewAndAddCreditMemos
  Scenario: Sales Manager view and add credit memos
    Given sales manager is on the credit memos page
    When click on the reset filter and views tab
    Then Sales manager should be able to view credit memos successfully
     And sales manager add credit memos
    Then sales manager successfully added credit memos