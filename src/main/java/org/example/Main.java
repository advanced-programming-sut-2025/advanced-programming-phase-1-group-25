package org.example;

import org.example.Enums.GameConsts.Gender;
import org.example.Models.App;
import org.example.Models.Item.ItemLoader;
import org.example.Models.Question;
import org.example.Models.User;
import org.example.Views.AppView;

import java.util.ArrayList;


/*
    Just starts the program!
 */

public class Main {
    public static void main(String[] args) {
        AppView appView = new AppView();
        AppView.run();
    }
}