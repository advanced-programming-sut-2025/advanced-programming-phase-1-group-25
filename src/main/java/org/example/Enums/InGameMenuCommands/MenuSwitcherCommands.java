package org.example.Enums.InGameMenuCommands;

import org.example.Controllers.InGameMenuController.MenuSwitcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MenuSwitcherCommands {
    MENU_ENTER("^\\s*menu\\s+enter\\s+(?<menu>.+?)\\s*$");
    private final String pattern;

    MenuSwitcherCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
