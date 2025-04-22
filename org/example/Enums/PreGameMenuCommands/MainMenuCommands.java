package org.example.Enums.PreGameMenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    Go_TO_AvatarMenu("go\\s+to\\s+avatar\\s+menu"),
    Go_To_ProfileMenu("go\\s+to\\s+profile\\s+menu"),
    Go_To_GameMenu("go\\s+to\\s+game\\s+menu"),
    User_Logout("^user\\s+logout");

    private final String pattern;

    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
