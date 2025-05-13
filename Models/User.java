package org.example.Models;

import org.example.Enums.GameConsts.Gender;

import java.util.ArrayList;

/*
    Each user is an object which have its own name, username, email, and many other fields.
 */
public class User {
    private String nickname;
    private String username;
    private String password;
    private String email;
    private Gender gender;
    private ArrayList<GameHistory> history;
    private Question securityQuestion;
    private boolean isInAnyGame;

    public User(String name, String username, String password, String email, Gender gender, Question userSecurityQuestion) {
        this.nickname = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.history = new ArrayList<>();
        this.securityQuestion = userSecurityQuestion;
        this.isInAnyGame = false;
    }

    public ArrayList<GameHistory> getGameHistory() {
        return history;
    }

    public void setGameHistory(GameHistory history) {
        this.history.add(history);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return nickname;
    }

    public void setName(String name) {
        this.nickname = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSecurityQuestion(Question securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public Question getSecurityQuestion() {
        return securityQuestion;
    }
    public boolean isInAnyGame() {
        return isInAnyGame;
    }
    public void entersGame() {
        this.isInAnyGame = true;
    }
    public void exitsGame() {
        this.isInAnyGame = false;
    }
}
