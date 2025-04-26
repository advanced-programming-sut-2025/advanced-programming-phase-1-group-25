package org.example;

import org.example.Enums.GameConsts.Gender;
import org.example.Models.App;
import org.example.Models.Question;
import org.example.Models.User;
import org.example.Views.AppView;


/*
    Just starts the program!
 */

public class Main {
    public static void main(String[] args) {
        User user1 = new User("ali1", "ali1", "1234", "", Gender.FEMALE, new Question("", ""));
        User user2 = new User("ali2", "ali2", "", "", Gender.FEMALE, new Question("", ""));
        User user3 = new User("ali3", "ali3", "", "", Gender.FEMALE, new Question("", ""));
        User user4 = new User("ali4", "ali4", "", "", Gender.FEMALE, new Question("", ""));
        App.addUser("ali1", user1);
        App.addUser("ali2", user2);
        App.addUser("ali3", user3);
        App.addUser("ali4", user4);

        AppView appView = new AppView();
        appView.run();
    }
}