package arustamov.page;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class TermsOfUsePage extends PageObject {

    private static String PAGE_CONTENT_CSS = ".terms-of-use";

    public Boolean isDisplayed() {
        return getRenderedView().elementIsCurrentlyVisible(
            By.cssSelector(PAGE_CONTENT_CSS)
        );
    }
}
