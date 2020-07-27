package arustamov.step.definition;

import arustamov.context.SpringContextAware;
import arustamov.enums.SessionVariable;
import arustamov.enums.SortOption;
import arustamov.step.Steps;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Shared;

public class WhenStepDefinitions implements SpringContextAware {

    @Shared
    private Steps steps;

    @When("^I open the application$")
    public void i_open_the_application() {
        steps.openApplication();
    }

    @When("^I go to contests$")
    public void i_go_to_contests() {
        steps.goToContests();
        steps.waitForScreenTransition();
    }

    @When("^I go to terms of service$")
    public void i_go_to_terms_of_service() {
        steps.goToTermsOfService();
        steps.waitForProgressBarDisplayed(false);
        steps.waitForScreenTransition();
    }

    @When("^I go to privacy policy$")
    public void i_go_to_privacy_policy() {
        steps.goToPrivacyPolicy();
        steps.waitForProgressBarDisplayed(false);
        steps.waitForScreenTransition();
    }

    @When("^I dismiss contests introduction$")
    public void i_dismiss_contests_introduction() {
        steps.dismissContestsIntroduction();
        steps.waitForScreenTransition();
    }

    @When("^I go to sort contests$")
    public void i_go_to_sort_contests() {
        steps.swipeToCollapsingToolbar();
        steps.expandSortOptions();
        steps.waitForScreenTransition();
    }

    @When("^I switch the sort option$")
    public void i_switch_the_sort_option() {
        SortOption currentSortOption = Serenity.sessionVariableCalled(SessionVariable.SORT_OPTION);

        SortOption newSortOption = SortOption.not(currentSortOption);

        steps.selectSortOption(newSortOption);
        steps.waitForProgressBarDisplayed(false);
        steps.waitForScreenTransition();
    }
}
