package home.dkio.iamsadbot.scenario;


import home.dkio.iamsadbot.utils.DialogTypes;
import home.dkio.iamsadbot.utils.ScenarioTypes;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.validation.constraints.NotNull;

@Slf4j
public final class Scenario {

    public static SendMessage execute(@NonNull Long tmId, @NotNull ScenarioTypes scenarioTypes) {
        switch (scenarioTypes) {
            case EXCELLENT:
                return SendMessage.builder().text(DialogTypes.EXCELLENT_FOR_YOU)
                        .chatId(String.valueOf(tmId))
                        .build();
            case GOOD:
                return SendMessage.builder().text(DialogTypes.GOOD_FOR_YOU)
                        .chatId(String.valueOf(tmId))
                        .build();
            case NORMAL:
                return SendMessage.builder().text(DialogTypes.NORMAL_FOR_YOU)
                        .chatId(String.valueOf(tmId))
                        .build();
            case BAD:
                return SendMessage.builder().text(DialogTypes.BAD_FOR_YOU)
                        .chatId(String.valueOf(tmId))
                        .build();
            default:
                return null;
        }
    }
}
