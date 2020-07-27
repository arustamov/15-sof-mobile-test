package arustamov.screen.container;

import arustamov.screen.MobilePageObject;
import org.openqa.selenium.By;

public class Toolbar extends MobilePageObject {

    private static final String CONTAINER_ID = "toolbar";

    public String getText() {
        return find(
            By.id(CONTAINER_ID)
        )
            .then(
                By.className(MobilePageObject.TEXT_VIEW_CLASS)
            )
            .getText();
    }

}
