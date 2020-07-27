package arustamov.enums;

import java.util.Arrays;
import java.util.stream.Stream;

public enum SortOption {

    MOST_RECENT("Most Recent"),
    MOST_POPULAR("Most Popular")
    ;

    private String text;

    SortOption(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static SortOption of(String text) {
        return Arrays.stream(
            SortOption.values()
        )
            .filter(
                sortOption -> sortOption.text.equalsIgnoreCase(text)
            )
            .findFirst()
            .get();
    }

    public static SortOption not(SortOption sortOption) {
        return Stream.of(
            SortOption.values()
        )
            .filter(
                so -> so != sortOption
            )
            .findFirst()
            .get();
    }
}
