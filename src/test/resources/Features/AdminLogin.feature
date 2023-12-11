@UITest @SmokeTest @RegressionTest
Feature: Admin User Login Test
  Background:
    Given Admin user navigate to the admin login panel

    Scenario: Sales manager login with valid credentials
      Given sales manager have valid credentials to login
      When sales manager enter valid username and valid password
      And sales manager click on login button
      Then sales manager should be on the dashboard page.

