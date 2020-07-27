package arustamov.screen.container;

import arustamov.screen.MobilePageObject;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class ContestVideoContainer extends MobilePageObject {

    private static final String VIDEO_DESCRIPTION_ID = "videoDescriptionTxt";

    public List<String> getVideoDescriptions() {
        return findAll(
            By.id(VIDEO_DESCRIPTION_ID)
        )
            .stream()
            .map(
                WebElementFacade::getText
            )
            .collect(
                Collectors.toList()
            );
    }
}
