package home.dkio.iamsadbot.utils;

import javax.validation.constraints.NotNull;

import static home.dkio.iamsadbot.domain.Moods.*;

public final class ScenarioMapping {

    public static ScenarioTypes mapMoodOnScenario(@NotNull String mood) {
        if (EXCELLENT.getName().equals(mood)) return ScenarioTypes.EXCELLENT;

        if (GOOD.getName().equals(mood)) return ScenarioTypes.GOOD;

        if (NORMAL.getName().equals(mood)) return ScenarioTypes.NORMAL;

        if (BAD.getName().equals(mood)) return ScenarioTypes.BAD;

        throw new IllegalArgumentException("Not in MOODS: " + mood);
    }
}