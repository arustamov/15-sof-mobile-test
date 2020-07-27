@contest
Feature: Contest

  As ...
  I want to be able to participate in contests
  In order to ...

  @automated
  Scenario: User see that all “Contest” elements have an “Enter now” button and a “Live” indicator

    When I open the application
    And I go to contests
    And I dismiss contests introduction

    Then I should see contests
    And I should see the contests have enter now button and live indicator
