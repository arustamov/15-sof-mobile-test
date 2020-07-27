@eula
Feature: Terms of Use and Privacy Policy

  As ...
  I want a user to be able to see Terms of Use and Privacy Policy
  In order to ...

  @automated
  Scenario: User see Terms of Use

    When I open the application
    And I go to terms of service

    Then I should see terms of use

  @automated
  Scenario: User see Privacy Policy

    When I open the application
    And I go to privacy policy

    Then I should see privacy policy
