package home.dkio.iamsadbot.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioMappingTest {

    @Test
    void mapMoodOnScenario() {
        ScenarioTypes scenarioTypes = ScenarioMapping.mapMoodOnScenario("id= " + "1" + ", user: " + "userName");
        Assertions.assertEquals(ScenarioTypes.SEND_WISH_TO_USER,scenarioTypes);
    }
}