package arustamov.step;

import arustamov.context.SpringContextAware;
import arustamov.enums.SortOption;
import arustamov.page.PrivacyPolicyPage;
import arustamov.page.TermsOfUsePage;
import arustamov.screen.AnyScreen;
import arustamov.screen.StartScreen;
import arustamov.screen.container.BottomSheet;
import arustamov.screen.container.CollapsingToolbar;
import arustamov.screen.container.ContestContainer;
import arustamov.screen.container.ContestVideoContainer;
import arustamov.screen.container.ProgressBar;
import arustamov.screen.container.RecyclerView;
import arustamov.screen.container.ScrollableList;
import arustamov.screen.container.Toolbar;
import io.appium.java_client.AppiumDriver;
import java.util.Set;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import static org.assertj.core.condition.AnyOf.anyOf;
import org.springframework.beans.factory.annotation.Value;

public class Steps implements SpringContextAware {

    @Value("${swipe.percent}")
    private Integer swipePercent;

    private AnyScreen anyScreen;
    private StartScreen startScreen;
    private CollapsingToolbar collapsingToolbar;

    private TermsOfUsePage termsOfUsePage;
    private PrivacyPolicyPage privacyPolicyPage;

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private BottomSheet bottomSheet;
    private RecyclerView recyclerView;
    private ContestContainer contestContainer;
    private ContestVideoContainer contestVideoContainer;

    @Step
    public void openApplication() {
        anyScreen.waitFor(
            driver -> anyScreen.isDisplayed()
        );
    }

    @Step
    public void switchToWebView() {
        final AppiumDriver proxiedDriver = ThucydidesWebDriverSupport.getProxiedDriver();
        final Set<String> contextHandles = proxiedDriver.getContextHandles();
        proxiedDriver.context(
            contextHandles
                .stream()
                .filter(
                    ctx -> ctx.startsWith("WEBVIEW")
                )
                .findFirst()
                .get()
        );
    }

    @Step
    public void switchToNativeApp() {
        final AppiumDriver proxiedDriver = ThucydidesWebDriverSupport.getProxiedDriver();
        final Set<String> contextHandles = proxiedDriver.getContextHandles();
        proxiedDriver.context(
            contextHandles
                .stream()
                .filter(
                    ctx -> ctx.equals("NATIVE_APP")
                )
                .findFirst()
                .get()
        );
    }

    @Step
    public void waitForScreenTransition() {
        anyScreen.waitForScreenRendered();
    }

    @Step
    public void waitForProgressBarDisplayed(final Boolean displayed) {
        progressBar.waitFor(
            driver -> displayed.equals(
                progressBar.isDisplayed()
            )
        );
    }

    @Step
    public void goToContests() {
        startScreen.tapContinue();
    }

    @Step
    public void goToTermsOfService() {
        startScreen.tapTermsOfService();
    }

    @Step
    public void goToPrivacyPolicy() {
        startScreen.tapPrivacyPolicy();
    }

    @Step
    public void dismissContestsIntroduction() {
        bottomSheet.tapGotIt();
    }

    @Step
    public void swipeToTop() {
        recyclerView.swipeUntil(
            () -> progressBar.isDisplayed(),
            ScrollableList.Direction.UP,
            swipePercent
        );
    }

    @Step
    public void swipeToContest(final String contest) {
        recyclerView.swipeUntil(
            () -> contestContainer.isTitleDisplayed(contest),
            ScrollableList.Direction.DOWN,
            swipePercent
        );
    }

    @Step
    public void swipeToCollapsingToolbar() {
        recyclerView.swipeUntil(
            () -> collapsingToolbar.isDisplayed(),
            ScrollableList.Direction.UP,
            swipePercent
        );
    }

    @Step
    public void expandSortOptions() {
        collapsingToolbar.tapSort();
    }

    @Step
    public void selectSortOption(SortOption sortOption) {
        switch(sortOption) {
            case MOST_RECENT:
                bottomSheet.tapMostRecent();
                break;
            case MOST_POPULAR:
                bottomSheet.tapMostPopular();
                break;
            default:
                throw new RuntimeException("Sort option is NOT supported");
        }
    }

    @Step
    public void verifyToolbarText(String text) {
        Assertions.assertThat(
            toolbar.getText()
        )
            .as("Toolbar text")
            .isEqualTo(text);
    }

    @Step
    public void verifyTermsOfUseDisplayed() {
        Assertions.assertThat(
            termsOfUsePage.isDisplayed()
        )
            .as("Terms Of Use displayed")
            .isTrue();
    }

    @Step
    public void verifyPrivacyPolicyDisplayed() {
        Assertions.assertThat(
            privacyPolicyPage.isDisplayed()
        )
            .as("Privacy Policy displayed")
            .isTrue();
    }

    @Step
    public Set<String> verifyContestsDisplayed() {
        final Set<String> contests = recyclerView.swipeCollect(
            () -> contestContainer.getTitles(),
            ScrollableList.Direction.DOWN,
            swipePercent
        );

        Assertions.assertThat(contests)
            .as("Contests displayed")
            .isNotEmpty();

        return contests;
    }

    @Step
    public void verifyContestLiveIndicatorDisplayed(String title) {
        Assertions.assertThat(
            contestContainer.getState(title)
        )
            .as("Contest live indicator displayed")
            .isEqualTo("Live");
    }

    @Step
    public void verifyContestEnterNowButtonDisplayed(String title) {
//        Assertions.assertThat(
//            contestContainer.isEnterNowDisplayed(title)
//        )
//            .as("Contest enter now button displayed")
//            .isTrue();

        Condition<ContestContainer> contestEnterNowButtonDisplayedBeforeSwipe = new Condition<>(
            cc -> cc.isEnterNowDisplayed(title),
            "Contest enter now button displayed before swipe"
        );
        Condition<ContestContainer> contestEnterNowButtonDisplayedAfterSwipe = new Condition<>(
            cc -> {
                recyclerView.swipe(ScrollableList.Direction.DOWN, 25);
                return cc.isEnterNowDisplayed(title);
            },
            "Contest enter now button displayed after swipe"
        );
        Assertions.assertThat(contestContainer)
            .is(
                anyOf(
                    contestEnterNowButtonDisplayedBeforeSwipe,
                    contestEnterNowButtonDisplayedAfterSwipe
                )
            );
    }

    @Step
    public SortOption verifySortOptionSelected() {
        final String checkedSortOption = bottomSheet.getCheckedSortOption();

        Assertions.assertThat(checkedSortOption)
            .as("Sort option selected")
            .isNotEmpty();

        return SortOption.of(checkedSortOption);
    }

    @Step
    public Set<String> verifyContestVideoDescriptionsDisplayed() {
        final Set<String> videoDescriptions = recyclerView.swipeCollect(
            () -> contestVideoContainer.getVideoDescriptions(),
            ScrollableList.Direction.DOWN,
            swipePercent
        );

        Assertions.assertThat(videoDescriptions)
            .as("Contest video descriptions displayed")
            .isNotEmpty();

        return videoDescriptions;
    }

    @Step
    public Set<String> verifyContestVideoDescriptionsInOrder(final Set<String> expectedContestVideoDescriptions) {
        final Set<String> currentVideoDescriptions = recyclerView.swipeCollect(
            () -> contestVideoContainer.getVideoDescriptions(),
            ScrollableList.Direction.DOWN,
            swipePercent
        );

        Assertions.assertThat(currentVideoDescriptions)
            .as("Contest video descriptions in order")
            .containsSequence(expectedContestVideoDescriptions);


        return currentVideoDescriptions;
    }

    @Step
    public Set<String> verifyContestVideoDescriptionsNotInOrder(final Set<String> expectedContestVideoDescriptions) {
        final Set<String> currentVideoDescriptions = recyclerView.swipeCollect(
            () -> contestVideoContainer.getVideoDescriptions(),
            ScrollableList.Direction.DOWN,
            swipePercent
        );

        Assertions.assertThat(currentVideoDescriptions)
            .as("Contest video descriptions NOT in order")
            .doesNotContainSequence(expectedContestVideoDescriptions);


        return currentVideoDescriptions;
    }
}
