package home.dkio.iamsadbot.service.impl.scenarios;

import home.dkio.iamsadbot.service.impl.AbstactScenario;
import home.dkio.iamsadbot.utils.DialogTypes;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GoodScenarioImpl extends AbstactScenario {
    private final Update update;

    public GoodScenarioImpl(Update update) {
        this.update = update;
    }

    @Override
    public SendMessage getMessage() {
        return SendMessage.builder().text(DialogTypes.GOOD_FOR_YOU)
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .build();
    }
}
