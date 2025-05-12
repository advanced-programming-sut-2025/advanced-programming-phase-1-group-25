package org.example.Enums.GameConsts;

/*
    We have just 4 seasons in the game.
 */
public enum Seasons {
    SPRING(0),
    SUMMER(1),
    FALL(2),
    WINTER(3);

    private final int numberOfSeason;

    Seasons(int numberOfSeason) {
        this.numberOfSeason = numberOfSeason;
    }

    public int getNumberOfSeason() {
        return numberOfSeason;
    }

    public static Seasons getSeasonByNumber(int numberOfSeason) {
        for (Seasons season : Seasons.values()) {
            if (season.numberOfSeason == numberOfSeason) {
                return season;
            }
        }
        return null;
    }
}
