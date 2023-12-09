@UITest @RegressionTest @SalesModule
Feature: Sales module features

  Background:
    Given sales manager login
    And sales manager on the dashboard page

    @ManageShoppingCart
    Scenario: Sales Manager should be able to manage view shopping cart for customers.
      description: (You need to open a customer and manage shopping cart) -Aliye
      Given sales manager is on customer's shopping cart page "aliyeidiris@gmail.com"
      When sales manager empty Customer's Shopping Cart "hbm001"
      Then sales manager should be able to manage customer's shopping cart successfully
