package org.example.Models.States;

/*
    A static class to store current weather state, change and return it when needed.
 */
public abstract class Weather {
    private static Weather currentWeather;

    public static Weather getCurrentWeather() {
        return currentWeather;
    }

    public static void setCurrentWeather(Weather weather) {
        Weather.currentWeather = weather;
    }
}
