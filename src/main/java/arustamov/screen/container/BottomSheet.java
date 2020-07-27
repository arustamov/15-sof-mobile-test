package arustamov.screen.container;

import arustamov.screen.MobilePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByAll;

public class BottomSheet extends MobilePageObject {

    private static final String CONTAINER_ID = "design_bottom_sheet";
    private static final String GOT_IT_BUTTON_ID = "submitBtn";
    private static final String MOST_RECENT_BUTTON_ID = "mostRecentBtn";
    private static final String MOST_POPULAR_BUTTON_ID = "mostPopularBtn";

    public void tapGotIt() {
        find(
            By.id(CONTAINER_ID)
        )
            .then(
                By.id(GOT_IT_BUTTON_ID)
            )
            .click();
    }

    public String getCheckedSortOption() {
        return findEach(
            new ByAll(
                By.id(MOST_RECENT_BUTTON_ID),
                By.id(MOST_POPULAR_BUTTON_ID)
            )
        )
            .filter(
                el -> Boolean.parseBoolean(
                    el.getAttribute("checked")
                )
            )
            .findFirst()
            .get()
            .getText();
    }

    public Boolean isMostRecentChecked() {
        return Boolean.parseBoolean(
            find(
                By.id(MOST_RECENT_BUTTON_ID)
            )
                .getAttribute("checked")
        );
    }

    public void tapMostRecent() {
        find(
            By.id(MOST_RECENT_BUTTON_ID)
        )
            .click();
    }

    public Boolean isMostPopularChecked() {
        return Boolean.parseBoolean(
            find(
                By.id(MOST_POPULAR_BUTTON_ID)
            )
                .getAttribute("checked")
        );
    }

    public void tapMostPopular() {
        find(
            By.id(MOST_POPULAR_BUTTON_ID)
        )
            .click();
    }
}
