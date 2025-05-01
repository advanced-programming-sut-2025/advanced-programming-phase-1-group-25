package org.example.Models.States;

import org.example.Enums.GameConsts.DayOfWeek;
import org.example.Enums.GameConsts.Seasons;


/*
    A static class to store current time, change and return it when needed.
 */
public class DateTime {
    private int day; // day in month - from 1 to 21
    private Seasons season; // current season - 4 seasons
    private int hour; // from 9 to 22
    private DayOfWeek dayOfWeek; // from Monday to Saturday


    public DateTime() {
        this.day = 1;
        this.season = Seasons.SPRING;
        this.hour = 9;
        this.dayOfWeek = DayOfWeek.MONDAY;
    }

    public void updateTimeByDay(int day) {
        int seasonPassed = (this.day + day) / 21;
        int newSeasonNumber = ((this.season.getNumberOfSeason() + seasonPassed) % 4) + 1;
        Seasons newSeason = Seasons.getSeasonByNumber(newSeasonNumber);
        this.season = newSeason;

        int newDayOfWeekNumber = ((this.dayOfWeek.getNumberOfDayInWeek() + day) % 7) + 1;
        DayOfWeek newDayOfWeek = DayOfWeek.getDayOfWeekByNumber(newDayOfWeekNumber);
        this.dayOfWeek = newDayOfWeek;

        this.day = ((this.day + day) % 21) + 1;

        this.hour = 9;
    }
    public void updateTimeByHour(int hour) {
        int dayPassed = (this.hour - 9 + hour) / 13;
        int newHour = ((this.hour - 9 + hour) % 13) + 9;
        updateTimeByDay(dayPassed);
        this.hour = newHour;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public Seasons getSeason() {
        return season;
    }
}
