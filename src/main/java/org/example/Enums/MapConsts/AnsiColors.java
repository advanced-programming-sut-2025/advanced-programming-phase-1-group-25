package org.example.Enums.MapConsts;

public enum AnsiColors {
    RESET("\u001B[0m", "\u001B[0m"),
    BLACK("\u001B[30m", "\u001B[40m"),
    RED("\u001B[31m", "\u001B[41m"),
    GREEN("\u001B[32m", "\u001B[42m"),
    YELLOW("\u001B[33m", "\u001B[43m"),
    BLUE("\u001B[34m", "\u001B[44m"),
    PURPLE("\u001B[35m", "\u001B[45m"),
    CYAN("\u001B[36m", "\u001B[46m"),
    WHITE("\u001B[37m", "\u001B[47m");

    private final String foregroundCode;
    private final String backgroundCode;

    AnsiColors(String foregroundCode, String backgroundCode) {
        this.foregroundCode = foregroundCode;
        this.backgroundCode = backgroundCode;
    }

    public String wrap(String text) {
        return foregroundCode + text + RESET.foregroundCode;
    }

    public String wrapBackground(String text) {
        return backgroundCode + text + RESET.backgroundCode;
    }

    public static String wrap(String text, AnsiColors fg, AnsiColors bg) {
        return fg.foregroundCode + bg.backgroundCode + text + RESET.foregroundCode + RESET.backgroundCode;
    }

    public String getForegroundCode() {
        return foregroundCode;
    }

    public String getBackgroundCode() {
        return backgroundCode;
    }
}
