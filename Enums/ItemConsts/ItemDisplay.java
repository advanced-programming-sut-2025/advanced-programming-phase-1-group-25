package org.example.Enums.ItemConsts;

public enum ItemDisplay {

    floor("."),
    home("H"),
    cottage("C"),
    lake("L"),
    quarry("Q"),
    greenhouse("G"),
    rock("R"),
    wood("W"),
    fiber("B"),
    tree("T"),
    foraging_seeds("S"),
    foraging_minerals("R"),
    foraging_crops("C"),
    shop("S"),
    barn_animal("b"),
    coop_animal("c"),
    all_crops("P"),
    building("t");
    private final String display;

    ItemDisplay(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public static String getDisplayByType(ItemType type) {
        for (ItemDisplay itemDisplay : ItemDisplay.values()) {
            if (itemDisplay.name().equalsIgnoreCase(type.name())) {
                return itemDisplay.getDisplay();
            }
        }
        return "?";
    }
}
