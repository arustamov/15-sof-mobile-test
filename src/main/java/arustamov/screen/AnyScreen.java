package arustamov.screen;

public class AnyScreen extends MobilePageObject {

    public Boolean isDisplayed() {
        return getRenderedElementsCount() > 0;
    }

    public void waitForScreenRendered() {
        waitFor(
            driver -> getPageSource().equals(
                getPageSource()
            )
        );
    }
}
