package org.example.Enums.InGameMenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ActionMenuCommands {
    NEXT_TURN("^\\s*next\\s+turn\\s*$"),
    TIME("^\\s*time\\s*$"),
    DATE("^\\s*date\\s*$"),
    DATE_TIME("^\\s*datetime\\s*$"),
    DAY_OF_THE_WEEK("^\\s*day\\s+of\\s+the\\s+week\\s*$"),
    CHEAT_ADVANCE_TIME("^\\s*cheat\\s+advance\\s+time\\s+(?<hours>\\.+)\\s*h\\s*$"),
    CHEAT_ADVANCE_DATE("^\\s*cheat\\s+advance\\s+date\\s+(?<day>\\.+)\\s*d\\s*$"),
    SEASON("^\\s*season\\s*$"),
    CHEAT_THOR("^\\s*cheat\\s+Thor\\s+-l\\s+(?<y>.+?)\\s+(?<x>.+?)\\s*$"),
    WEATHER("^\\s*weather\\s*$"),
    WEATHER_FORECAST("^\\s*weather\\s+forecast\\s*$"),
    CHEAT_WEATHER_SET("^\\s*cheat\\s+weather\\s+set\\s+(?<type>.+?)\\s*$"),
    GREENHOUSE_BUILD("^\\s*greenhouse\\s+build\\s*$"),
    WALK("^\\s*walk\\s+-l\\s+(?<y>.+?)\\s+(?<x>.+?)\\s*$"),
    PRINT_MAP("^\\s*print\\s+map\\s+-l\\s+(?<y>.+?)\\s+(?<x>.+?)\\s+-s\\s+(?<size>.+?)\\s*$"),
    HELP_READING_MAP("^\\s*help\\s+reading\\s+map\\s*$"),
    ENERGY_SHOW("^\\s*energy\\s+show\\s*$"),
    ENERGY_SET("^\\s*energy\\s+set\\s+-v\\s+(?<value>.+?)\\s*$"),
    ENERGY_UNLIMITED("^\\s*energy\\s+unlimited\\s*$"),
    INVENTORY_SHOW("^\\s*inventory\\s+show\\s*$"),
    INVENTORY_TRASH("^\\s*inventory\\s+trash(\\s+-i\\s+(?<itemName>.+?))?\\s+-n\\s+(?<number>.+?)\\s*$"),
    TOOLS_EQUIP("^\\s*tools\\s+equip\\s+(?<toolName>.+?)\\s*$"),
    TOOLS_SHOW_CURRENT("^\\s*tools\\s+show\\s+current\\s*$"),
    TOOLS_SHOW_AVAILABLE("^\\s*tools\\s+show\\s+available\\s*$"),
    TOOLS_UPGRADE("^\\s*tools\\s+upgrade\\s+(?<toolName>.+?)\\s*$"),
    TOOLS_USE("^\\s*tools\\s+use\\s+-d\\s+(?<direction>.+?)\\s*$"),
    CRAFT_INFO("^\\s*craft\\s+info\\s+-n\\s+(?<craftName>.+?)\\s*$"),
    PLANT("^\\s*plant\\s+-s\\s+(?<seed>.+?)\\s+-d\\s+(?<direction>.+?)\\s*$"),
    SHOW_PLANT("^\\s*showplant\\s+-l\\s+(?<y>.+?)\\s+(?<x>.+?)\\s*$"),
    FERTILIZE("^\\s*fertilize\\s+-f\\s+(?<fertilizer>.+?)\\s+-d\\s+(?<direction>.+?)\\s*$"),
    HOW_MUCH_WATER("^\\s*howmuch\\s+wate\\s*$"),
    CRAFTING_SHOW_RECIPES("^\\s*crafting\\s+show\\s+recipes\\s*$"),
    CRAFTING_CRAFT("^\\s*crafting\\s+craft\\s+(?<itemName>.+?)\\s*$"),
    PLACE_ITEM("^\\s*place\\s+item\\s+-n\\s+(?<itemName>.+?)\\s+-d\\s+(?<direction>.+?)\\s*$"),
    CHEAT_ADD_ITEM("^\\s*cheat\\s+add\\s+item\\s+-n\\s+(?<itemName>.+?)\\s+-c\\s+(?<count>.+?)\\s*$"),
    COOKING_REFRIGERATOR("^\\s*cooking\\s+refrigerator\\s+(put/pick)\\s+(?<item>.+?)\\s*$"),
    COOKING_SHOW_RECIPES("^\\s*cooking\\s+show\\s+recipes\\s*$"),
    COOKING_PREPARE("^\\s*cooking\\s+prepare\\s+(?<recipeName>.+?)\\s*$"),
    EAT("^\\s*eat\\s+(?<foodName>.+?)\\s*$"),
    BUILD("^\\s*build\\s+-a\\s+(?<buildingName>.+?)\\s+-l\\s+(?<y>.+?)\\s+(?<x>.+?)\\s*$"),
    BUY_ANIMAL("^\\s*buy\\s+animal\\s+-a\\s+(?<animal>.+?)\\s+-n\\s+(?<name>.+?)\\s*$"),
    PET("^\\s*pet\\s+-n\\s+(?<name>.+?)\\s*$"),
    CHEAT_SET_FRINDSHIP("^\\s*cheat\\s+set\\s+friendship\\s+-n\\s+(?<animalName>.+?)\\s+-c\\s+(<amount>.+?)\\s*$"),
    ANIMALS("^\\s*animals\\s*$"),
    SHEPHERD_ANIMALS("^\\s*shepherd\\s+animals\\s+-n\\s+(<animalName>.+?)\\s+-l\\s+(?<y>.+?)\\s+(?<x>.+?)\\s*$"),
    FEED_HAY("^\\s*feed\\s+hay\\s+-n\\s+(?<animalName>.+?)\\s*$"),
    PRODUCES("^\\s*produces\\s*$"),
    COLLECT_PRODUCE("^\\s*collect\\s+produce\\s+-n\\s+(?<name>.+?)\\s*$"),
    SELL_ANIMAL("^\\s*sell\\s+animal\\s+-n\\s+(?<name>.+?)\\s*$"),
    FISHING("^\\s*fishing\\s+-p\\s+(?<fishingPole>.+?)\\s*$"),
    TRADE("^\\s*trade\\s+-u\\s+(?<username>.+?)\\s+-t\\s+(?<type>.+?)\\s+-i\\s+(?<item>.+?)\\s+-a\\s+" +
            "(?<amount>.+?)\\s+(-p\\s+(?<price>.+?)\\s*)|(-ti\\s+(?<targetItem>.+?)\\s+-ta\\s+(?<targetAmount>.+?)\\s*)$");

    private final String pattern;

    ActionMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) return matcher;
        return null;
    }
}
