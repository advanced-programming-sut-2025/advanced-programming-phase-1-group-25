package advanced.org.example.Models.States;

import advanced.org.example.Enums.GameConsts.DayOfWeek;
import advanced.org.example.Enums.GameConsts.Seasons;


/*
    A static class to store current time, change and return it when needed.
 */
public abstract class DateTime {
    private static int day; // day in month
    private static Seasons season; // current season
    private static int hour;
    private static DayOfWeek dayOfWeek;


    public static void updateTimeByDay(int day) {

    }
    public static void updateTimeByHour(int hour) {

    }
}
