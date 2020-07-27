package arustamov.screen.container;

public class RecyclerView extends ScrollableList {

    @Override
    protected String getScrollViewElementClass() {
        return "androidx.recyclerview.widget.RecyclerView";
    }
}
