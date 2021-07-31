package home.dkio.iamsadbot.utils;

import javax.validation.constraints.NotNull;

import static home.dkio.iamsadbot.entity.Moods.*;

public final class ScenarioMapping {

    public static ScenarioTypes mapMoodOnScenario(@NotNull String mood) {
        if (EXCELLENT.getName().equals(mood)) return ScenarioTypes.EXCELLENT;

        if (GOOD.getName().equals(mood)) return ScenarioTypes.GOOD;

        if (NORMAL.getName().equals(mood)) return ScenarioTypes.NORMAL;

        if (BAD.getName().equals(mood)) return ScenarioTypes.BAD;

        if (mood.equals(DialogTypes.SUPPORT)) return ScenarioTypes.SUPPORT;

        if (mood.equals(DialogTypes.NO)) return ScenarioTypes.NOTSUPPORT;

        if(mood.contains(KeyIdentifiers.id)) return ScenarioTypes.SEND_WISH_TO_USER;

        if(mood.contains(KeyIdentifiers.userName)) return ScenarioTypes.SUPPORT_USER;


        throw new IllegalArgumentException("Not in range: " + mood);
    }
}
