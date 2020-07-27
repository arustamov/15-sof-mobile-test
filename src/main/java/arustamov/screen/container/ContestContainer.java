package arustamov.screen.container;

import arustamov.screen.MobilePageObject;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class ContestContainer extends MobilePageObject {

    private static final String CONTAINER_ID = "contestView";
    private static final String TITLE_ID = "contestTitleTxt";
    private static final String STATE_TEXT_ID = "contestStateTxt";
    private static final String ENTER_NOW_BUTTON_ID = "contestEnterBtn";

    public List<String> getTitles() {
        return findAll(
            By.id(TITLE_ID)
        )
            .stream()
            .map(
                WebElementFacade::getText
            )
            .collect(
                Collectors.toList()
            );
    }

    public Boolean isTitleDisplayed(String title) {
        return findEach(
            By.id(TITLE_ID)
        )
            .anyMatch(
                e -> e.containsOnlyText(title)
            );
    }

    public String getState(String title) {
        return findEach(
            By.id(CONTAINER_ID)
        )
            .filter(
                e -> e.find(
                    By.id(TITLE_ID)
                )
                    .containsOnlyText(title)
            )
            .findFirst()
            .get()
            .find(
                By.id(
                    STATE_TEXT_ID
                )
            )
            .getText();
    }

    public Boolean isEnterNowDisplayed(String title) {
        return findEach(
            By.id(CONTAINER_ID)
        )
            .filter(
                e -> e.find(
                    By.id(TITLE_ID)
                )
                    .containsOnlyText(title)
            )
            .findFirst()
            .get()
            .containsElements(
                By.id(ENTER_NOW_BUTTON_ID)
            );
    }
}
