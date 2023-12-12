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
      Then sales manager should be able to manage customer's shopping cart successfully

      @ManageInvoicesAndComment
      Scenario: Sales manager should be able to view invoices and add comments
        Given Sales manager is on the dashboard page and invoices are should be existing
        When Sales manager views invoices and adds comments
        Then Sales manager should be able to view invoices and add comments successfully



