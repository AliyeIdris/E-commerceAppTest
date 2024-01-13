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

  @ProductsOrderedReport
  Scenario Outline: Reporting Manager should be able to see Products - Products Ordered Report
    Given reporting manager is on the Products Ordered page
    When reporting manager filter Products Ordered Report by date "<dateFrom>" and"<dateTo>"
    Then reporting manager should be able to see Products Ordered Report
    Examples:
      | dateFrom   | dateTo     |
      | 01/01/2022 | 04/01/2024 |

#abdugeni
  Scenario: Reporting Manager should be able to see Sales - Total Invoiced vs Paid Report
    Given  Reporting Manager Navigate To Total Invoiced vs Paid Report page
    When   Fill Out Criteria For Search
    Then Reporting Manager Can see Sales - Total Invoiced vs Paid Report

  Scenario Outline: Reporting manager can see coupons usage report
    Given The reporting manager opens coupons page
    When the coupons report should be displayed
    And  the manager fills in the filter details"<websiteName>" "<fromDate>" "<toDate>"
    Then no records found displayed

    Examples:
      | websiteName  | fromDate   | toDate     |
      | All Websites | 06/28/2023 | 12/28/2023 |
#*******

  @MostViewedProductsReport
  Scenario Outline: Reporting Manager should be able to see Products Most Viewed Report
    Given report manager is on the dashboard page
    When reporting manager navigate to most viewed page data with "<dateFrom>" and"<dateTo>"
    Then most viewed products should be displayed
    Examples:
      | dateFrom   | dateTo     |
      | 01/01/2022 | 31/12/2023 |

  @ViewProductInShoppingCartsReport
  Scenario:Reporting Manager should be able to see shopping cart-product in carts report
    Given reporting manager navigate to product in carts page
    When the page opened and report manager see the report
    Then  product in cart should be displayed

  @ViewTagsCustomersReport
  Scenario:Reporting Manager should be able to see Tags-customers report
    Given reporting manager navigate to Customers Tags page
    When the page opened and report manager sees the report of customer
    Then show customers Tags successful


  @SeeShoppingCart-AbandonedCartsReport
  Scenario: Reporting Manager should be able to see Shopping Cart - Abandoned carts Report
    Given reporting Manager Is Navigate To The AbandonedPage
    When  Reporting Manager Navigate to The Shopping Cart And Choose Abandoned carts
    Then  Reporting Manager Should Get Report From AbandonedCart
