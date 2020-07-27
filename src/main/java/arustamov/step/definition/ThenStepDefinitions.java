package arustamov.step.definition;

import arustamov.context.SpringContextAware;
import arustamov.enums.SessionVariable;
import arustamov.enums.SortOption;
import arustamov.step.Steps;
import cucumber.api.java.en.Then;
import java.util.Set;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Shared;

public class ThenStepDefinitions implements SpringContextAware {

    @Shared
    private Steps steps;

    @Then("^I should see terms of use$")
    public void i_should_see_terms_of_use() {
        steps.verifyToolbarText("Terms of Use");
        steps.switchToWebView();
        steps.verifyTermsOfUseDisplayed();
        steps.switchToNativeApp();
    }

    @Then("^I should see privacy policy$")
    public void i_should_see_privacy_policy() {
        steps.verifyToolbarText("Privacy Policy");
        steps.switchToWebView();
        steps.verifyPrivacyPolicyDisplayed();
        steps.switchToNativeApp();
    }

    @Then("^I should see contests$")
    public void i_should_see_contests() {
        final Set<String> contests = steps.verifyContestsDisplayed();

        Serenity.setSessionVariable(SessionVariable.CONTESTS).to(contests);
    }

    @Then("^I should see the contests have enter now button and live indicator$")
    public void i_should_see_the_contests_have_enter_now_button_and_live_indicator() {
        Set<String> contests = Serenity.sessionVariableCalled(SessionVariable.CONTESTS);

        contests.forEach(
            contest -> {
                steps.swipeToTop();
                steps.swipeToContest(contest);
                steps.verifyContestLiveIndicatorDisplayed(contest);
                steps.verifyContestEnterNowButtonDisplayed(contest);
            }
        );
    }

    @Then("^I should see contest videos$")
    public void i_should_see_contest_videos() {
        Set<String> contestVideoDescriptions = steps.verifyContestVideoDescriptionsDisplayed();

        Serenity.setSessionVariable(SessionVariable.CONTEST_VIDEO_DESCRIPTIONS).to(contestVideoDescriptions);
    }

    @Then("^I should see a sort option selected$")
    public void i_should_see_a_sort_option_selected() {
        final SortOption sortOption = steps.verifySortOptionSelected();

        Serenity.setSessionVariable(SessionVariable.SORT_OPTION).to(sortOption);
    }

    @Then("^I should see contest videos order is( not)? the same$")
    public void i_should_see_contest_videos_order_is_the_same(String not) {
        Set<String> contestVideoDescriptions = Serenity.sessionVariableCalled(SessionVariable.CONTEST_VIDEO_DESCRIPTIONS);
        if (not == null) {
            contestVideoDescriptions = steps.verifyContestVideoDescriptionsInOrder(contestVideoDescriptions);
        }
        else {
            contestVideoDescriptions = steps.verifyContestVideoDescriptionsNotInOrder(contestVideoDescriptions);
        }

        Serenity.setSessionVariable(SessionVariable.CONTEST_VIDEO_DESCRIPTIONS).to(contestVideoDescriptions);
    }
}
