package arustamov.enums;

public enum SessionVariable {

    CONTESTS,
    CONTEST_VIDEO_DESCRIPTIONS,
    SORT_OPTION
    ;

    public static SessionVariable of(String variable) {
        for (SessionVariable sv : SessionVariable.values()) {
            if (variable.toUpperCase().replaceAll(" ", "_").equals(sv.name())) {
                return sv;
            }
        }
        throw new RuntimeException(String.format("There is no '%s' session variable", variable));
    }
}
