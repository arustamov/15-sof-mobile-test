package arustamov.screen.container;

import arustamov.screen.MobilePageObject;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.Point;

public abstract class ScrollableList extends MobilePageObject {

    protected abstract String getScrollViewElementClass();

    public Boolean swipe(Direction direction, Integer percentage) {
        final Optional<WebElementFacade> scrollableElement = getScrollableElement(
            getScrollViewElementClass()
        );
        if (scrollableElement.isPresent()) {
            return swipe(scrollableElement.get(), direction, percentage);
        }
        return false;
    }

   public Boolean swipeUntil(BooleanSupplier until, Direction direction, Integer percentage) {
        Integer swipeAttempts = 3;
        Boolean swipeNext = true;

        final Optional<WebElementFacade> scrollViewElement = getScrollableElement(
            getScrollViewElementClass()
        );
        while (!until.getAsBoolean() && swipeNext) {
            if (scrollViewElement.isPresent()) {
                Boolean swiped = swipe(scrollViewElement.get(), direction, percentage);
                if (swiped) {
                    swipeAttempts = 3;
                } else {
                    swipeAttempts--;
                }
                swipeNext = swipeAttempts > 0;
            } else {
                swipeNext = false;
            }
        }
        return swipeNext;
    }

    public <R> Set<R> swipeCollect(Supplier<List<R>> supplier, Direction direction, Integer percentage) {
        Set<R> result = new LinkedHashSet<>();
        Integer swipeAttempts = 3;
        Boolean swipeNext = true;

        final Optional<WebElementFacade> scrollViewElement = getScrollableElement(
            getScrollViewElementClass()
        );
        while (swipeNext) {
            final List<R> currentResult = supplier.get();
            result.addAll(currentResult);
            if (scrollViewElement.isPresent()) {
                Boolean swiped = swipe(scrollViewElement.get(), direction, percentage);
                if (swiped) {
                    swipeAttempts = 3;
                } else {
                    swipeAttempts--;
                }
                swipeNext = swipeAttempts > 0;
            } else {
                swipeNext = false;
            }
        }
        return result;
    }

    protected Boolean swipe(WebElementFacade scrollableElement, Direction direction, Integer percentage) {
        final String beforePageSource = getPageSource();
        swipeVertically(scrollableElement, direction, percentage);
        final String afterPageSource = getPageSource();

        return !beforePageSource.equals(afterPageSource);
    }

    private void swipeVertically(WebElementFacade swipeElement, Direction direction, Integer percentage) {
        Integer upperLeftY = swipeElement.getLocation().getY();
        Integer elementHeight = swipeElement.getSize().getHeight();
        Integer yFrom = direction.getYFrom(upperLeftY, elementHeight, percentage);
        Integer yTo = direction.getYTo(upperLeftY, elementHeight, percentage);
        swipeVertically(swipeElement, yFrom, yTo);
    }

    private void swipeVertically(WebElementFacade swipeElement, Integer yFrom, Integer yTo) {
        Integer x = swipeElement.getLocation().getX() + swipeElement.getSize().getWidth() / 2;
        Point from = new Point(x, yFrom);
        Point to = new Point(x, yTo);
        new TouchAction(
            (PerformsTouchActions) ((WebDriverFacade) getDriver())
                .getProxiedDriver()
        )
            .press(
                PointOption.point(from)
            )
            .waitAction()
            .moveTo(
                PointOption.point(to)
            )
            .release()
            .perform();
    }

    public enum Direction {
        UP {
            @Override
            public Integer getYFrom(final Integer upperLeftY, final Integer elementHeight, final Integer percentage) {
                return upperLeftY;
            }

            @Override
            public Integer getYTo(final Integer upperLeftY, final Integer elementHeight, final Integer percentage) {
                return upperLeftY + elementHeight * percentage / 100;
            }
        },
        DOWN {
            @Override
            public Integer getYFrom(final Integer upperLeftY, final Integer elementHeight, final Integer percentage) {
                return upperLeftY + elementHeight - 1;
            }

            @Override
            public Integer getYTo(final Integer upperLeftY, final Integer elementHeight, final Integer percentage) {
                return upperLeftY + elementHeight * (100 - percentage) / 100;
            }
        };

        public abstract Integer getYFrom(Integer upperLeftY, Integer elementHeight, Integer percentage);

        public abstract Integer getYTo(Integer upperLeftY, Integer elementHeight, Integer percentage);
    }
}
