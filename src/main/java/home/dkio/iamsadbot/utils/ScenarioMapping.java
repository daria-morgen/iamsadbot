package home.dkio.iamsadbot.utils;

import javax.validation.constraints.NotNull;

import static home.dkio.iamsadbot.domain.Moods.*;

public final class ScenarioMapping {

    public static ScenarioTypes mapMoodOnScenario(@NotNull String mood) {
        if (EXCELLENT.getName().equals(mood)) return ScenarioTypes.EXCELLENT;

        if (GOOD.getName().equals(mood)) return ScenarioTypes.GOOD;

        if (NORMAL.getName().equals(mood)) return ScenarioTypes.NORMAL;

        if (BAD.getName().equals(mood)) return ScenarioTypes.BAD;

        if (mood.equals(DialogTypes.SUPPORT)) return ScenarioTypes.SUPPORT;

        if (mood.equals(DialogTypes.NO)) return ScenarioTypes.NOTSUPPORT;

        if(mood.contains("user_for_support:")) return ScenarioTypes.SUPPORT_USER;

        if(mood.contains("wish =")) return ScenarioTypes.SEND_WISH_TO_USER;

        throw new IllegalArgumentException("Not in range: " + mood);
    }
}
