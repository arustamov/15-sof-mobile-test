package arustamov.screen.container;

import arustamov.screen.MobilePageObject;
import org.openqa.selenium.By;

public class CollapsingToolbar extends MobilePageObject {

    private static final String CONTAINER_ID = "collapsingToolbar";
    private static final String SORT_BUTTON_ID = "sortBtn";

    public Boolean isDisplayed() {
        return getRenderedView().elementIsCurrentlyVisible(
            By.id(CONTAINER_ID)
        );
    }

    public void tapSort() {
        find(
            By.id(SORT_BUTTON_ID)
        )
            .click();
    }
}
