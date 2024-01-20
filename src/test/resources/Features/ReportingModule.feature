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
    Then Products reports should be seeable

  @Manage_Products_Reviews
  Scenario: Reporting manager should be able to see products reviews
    When  reporting manager opens the reviews page and sees the reviews
    Then  products reviews should be seeable

  @Products-low-stock-Report  #Xamxinur.A
  Scenario: Reporting Manager should be able to see Products low stock Report
    When  reporting manager navigate to low stock page
    Then  products low stock report should be displayed

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
    When reporting manager filters customer report and searches for it
    Then reporting manager should be able to see Customers Report-New Accounts Report

  @SeeTagForProducts
  Scenario:  Reporting Manager should be able to see Tags for products
    When reporting manager see tags for products
    Then products tag should be visible on the reporting page


  @ProductsOrderedReport
  Scenario Outline: Reporting Manager should be able to see Products - Products Ordered Report
    Given reporting manager is on the Products Ordered page
    When reporting manager filter Products Ordered Report by date "<dateFrom>" and"<dateTo>"
    Then reporting manager should be able to see Products Ordered Report
    Examples:
      | dateFrom   | dateTo     |
      | 01/01/2022 | 04/01/2024 |

#abdugeni
  @SeeInvoicedAndReportTest
  Scenario Outline: Reporting Manager Should Be Able To See Sales - Total Invoiced VS Paid Report
    Given  reporting manager should be on the total invoiced vs paid report page
    When   Fill Out Criteria For Search "<period>" "<fromDate>" "<toDate>"
    Then Reporting Manager Can see Sales - Total Invoiced vs Paid Report

    Examples:
      | period  | fromDate   | toDate     |
      | Month | 06/28/2009 | 12/28/2023 |
  @SeeCouponsReportTest
  Scenario Outline: Reporting manager can see coupons usage report
    Given The reporting manager Should Be On The coupons Page and the coupons report should be displayed
    When the manager fills in the filter details"<websiteName>" "<fromDate>" "<toDate>"
    Then coupons records info is displayed

    Examples:
      | websiteName  | fromDate   | toDate     |
      | All Websites | 06/28/2009 | 12/28/2023 |

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
    When open the product in cart page
    Then  product in cart should be displayed

  @ViewTagsCustomersReport
  Scenario:Reporting Manager should be able to see Tags-customers report
    Given reporting manager navigate to Customers Tags page
    When click on the show tags in the report of customer
    Then show customers Tags successful


  @SeeShoppingCart-AbandonedCartsReport
  Scenario: Reporting Manager should be able to see Shopping Cart - Abandoned carts Report
    Given reporting Manager Is Navigate To The AbandonedPage
    When  Reporting Manager Navigate to The Shopping Cart And Choose Abandoned carts
    Then  Reporting Manager Should Get Report From AbandonedCart

  @SeeProductBestSellerReport
  Scenario: Reporting Manager should be able to see the best seller report
    When Reporting Manager see the best seller report
    Then Reporting Manager should see the best seller report

  @TotalOrderReport  #Safiya
  Scenario: Reporting Manager should be able to see Sales - Total Ordered Report
    Given reporting manager navigate to order report page
    When reporting manager filter total order report with date
    Then total order report should be displayed