package org.example.Enums.GameConsts;

public enum DayOfWeek {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int numberOfDayInWeek;

    DayOfWeek(int numberOfDayInWeek) {
        this.numberOfDayInWeek = numberOfDayInWeek;
    }

    public int getNumberOfDayInWeek() {
        return numberOfDayInWeek;
    }

    public static DayOfWeek getDayOfWeekByNumber(int numberOfDayInWeek) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.numberOfDayInWeek == numberOfDayInWeek) {
                return day;
            }
        }
        return null;
    }
}
