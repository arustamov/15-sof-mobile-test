@sort
Feature: Sort

  As ...
  I want to be able to sort contests
  In order to ...

  @automated
  Scenario: User changes the elements order by “Sorting” button

    When I open the application
    And I go to contests
    And I dismiss contests introduction

    Then I should see contest videos

    When I go to sort contests

    Then I should see a sort option selected

    When I switch the sort option

    Then I should see contest videos order is not the same

    When I go to sort contests

    Then I should see a sort option selected

    When I switch the sort option

    Then I should see contest videos order is not the same
