package arustamov.screen.container;

import arustamov.screen.MobilePageObject;
import org.openqa.selenium.By;

public class ProgressBar extends MobilePageObject {

    public Boolean isDisplayed() {
        return getRenderedView().elementIsCurrentlyVisible(
            By.className(PROGRESS_BAR_CLASS)
        );
    }
}
