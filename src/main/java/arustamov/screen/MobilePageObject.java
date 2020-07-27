package arustamov.screen;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import java.util.Optional;
import java.util.stream.Stream;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.By;

public class MobilePageObject extends PageObject {

    protected static final String PROGRESS_BAR_CLASS = "android.widget.ProgressBar";
    protected static final String IMAGE_VIEW_CLASS = "android.widget.ImageView";
    protected static final String BUTTON_CLASS = "android.widget.Button";
    protected static final String EDIT_TEXT_CLASS = "android.widget.EditText";
    protected static final String VIEW_CLASS = "android.view.View";
    protected static final String TEXT_VIEW_CLASS = "android.widget.TextView";
    protected static final String SCROLL_VIEW_CLASS = "android.widget.ScrollView";
    protected static final String VIEW_GROUP_CLASS = "android.view.ViewGroup";
    protected static final String HORIZONTAL_SCROLL_VIEW_CLASS = "android.widget.HorizontalScrollView";
    protected static final String SWITCH_CLASS = "android.widget.Switch";

    public String getPageSource() {
        return getDriver().getPageSource();
    }

    protected Integer getRenderedElementsCount() {
        return findAll(
            By.xpath("//*")
        )
            .size();
    }

    protected void tapCoordinates(Integer x, Integer y) {
        new TouchAction(
            (PerformsTouchActions) ((WebDriverFacade) getDriver())
                .getProxiedDriver()
        )
            .tap(
                PointOption.point(x, y)
            )
            .perform();
    }

    protected Stream<WebElementFacade> findByClass(String byClass) {
        return findEach(
            By.className(byClass)
        );
    }

    protected Stream<WebElementFacade> findByClass(WebElementFacade webElementFacade, String byClass) {
        return webElementFacade.thenFindAll(
            By.className(
                byClass
            )
        )
            .stream();
    }

    protected Boolean hasText(Stream<WebElementFacade> elements, String text) {
        return elements.anyMatch(
            e -> e.containsOnlyText(text)
        );
    }

    protected WebElementFacade filterByText(Stream<WebElementFacade> elements, String text) {
        return elements.filter(
            el -> el.containsOnlyText(text)
        )
            .findFirst()
            .get();
    }

    protected Stream<WebElementFacade> filterByAttribute(Stream<WebElementFacade> elements, String attribute) {
        return elements.filter(
            el -> Boolean.TRUE.equals(
                Boolean.valueOf(
                    el.getAttribute(
                        attribute
                    )
                )
            )
        );
    }

    protected Optional<WebElementFacade> getScrollableElement(String className) {
        return getScrollableElements(className).findFirst();
    }

    protected Stream<WebElementFacade> getScrollableElements(String className) {
        return filterByAttribute(
            findByClass(
                className
            ),
            "scrollable"
        );
    }
}
