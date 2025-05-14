package org.example.Models.States;

import org.example.Controllers.InGameMenuController.GenerateRandomNumber;
import org.example.Enums.GameConsts.Seasons;
import org.example.Enums.GameConsts.WeatherStates;
import org.example.Models.Game;

/*
    A static class to store current weather state, change and return it when needed.
 */
public class Weather {
    private WeatherStates currentWeather;

    public Weather() {
        this.currentWeather = WeatherStates.SUNNY;
    }

    public WeatherStates getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherStates weather) {
        this.currentWeather = weather;
    }


}
