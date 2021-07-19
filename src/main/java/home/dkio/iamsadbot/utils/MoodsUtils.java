package home.dkio.iamsadbot.utils;

import home.dkio.iamsadbot.domain.Moods;

import java.util.ArrayList;
import java.util.List;

public final class MoodsUtils {
    private static ArrayList<String> strings = new ArrayList<>();

    static {
        for (Moods value : Moods.values()) {
            strings.add(value.getCode());
        }
    }

    public static List<String> getArrayOfCodes() {
        return strings;
    }
}
