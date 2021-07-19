package home.dkio.iamsadbot.service.impl.scenarios;

import home.dkio.iamsadbot.service.Scenario;
import home.dkio.iamsadbot.service.impl.AbstactScenario;
import home.dkio.iamsadbot.utils.DialogTypes;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NormalScenarioImpl extends AbstactScenario {
    private final Update update;

    public NormalScenarioImpl(Update update) {
        this.update = update;
    }

    @Override
    public SendMessage getMessage() {
        return SendMessage.builder().text(DialogTypes.NORMAL_FOR_YOU)
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .build();
    }
}
