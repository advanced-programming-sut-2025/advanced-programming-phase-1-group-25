package advanced.org.example.Enums.PreGameMenuCommands;

import advanced.org.example.Enums.GameMenus.MenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupMenuCommands implements MenuCommands {
    CHANGE_MENU("^\\s*menu\\s+enter\\s+(?<menu>.+?)s*$"),
    MENU_EXIT("^\\s*menu\\s+exit\\s*$"),
    SHOW_CURRENT_MENU("^\\s*show\\s+current\\s+menu\\s*$"),
    REGISTER("^\\s*register\\s+-u\\s+(?<username>.+?)\\s+-p\\s+" +
            "(?<password>.+?)\\s+-n\\s+(?<nickname>.+?)\\s+-e\\s+(?<email>.+?)\\s+" +
            "-g\\s+(?<gender>.+?)\\s*$"),
    PICK_QUESTION("^\\s*pick\\s+question\\s+-q\\s+(?<question_number>.+?)\\s+" +
            "-a\\s+(?<answer>.+?)\\s+-c\\s+(?<answer_confirm>.+?)\\s*$");

    private final String pattern;

    SignupMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}