package org.example.Models;

public class GameHistory {
    private User user;
    private Game game;
    private int score;
    public GameHistory(User user, Game game, int score) {
        this.user = user;
        this.game = game;
        this.score = score;
    }
}
