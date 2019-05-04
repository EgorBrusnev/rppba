package by.rppba.production.util;

public enum Time {
    SECOND("sec", 1),
    MINUTE("min", 60),
    HOUR("hour", 3600),
    DAY("day", 3600 * 24),
    WEEK("week", 3600 * 24 * 7);

    private String ext;
    private int divider;

    Time(String ext, int divider) {
        this.ext = ext;
        this.divider = divider;
    }

    public String getExt() {
        return ext;
    }

    public int getDivider() {
        return divider;
    }
}
