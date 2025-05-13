package org.example.Controllers.UpdateMap;

import java.io.UTFDataFormatException;

public class MorningUpdate {
    public static void morningUpdate() {
        UpdateForaging.deleteForaging();
        UpdateForaging.updateForaging();
    }

}
