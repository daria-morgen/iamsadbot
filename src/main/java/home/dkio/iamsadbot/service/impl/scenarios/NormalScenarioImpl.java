package home.dkio.iamsadbot.service.impl.scenarios;

import home.dkio.iamsadbot.service.impl.AbstactScenario;
import home.dkio.iamsadbot.utils.DialogTypes;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NormalScenarioImpl extends AbstactScenario {
    private final Update update;

    public NormalScenarioImpl(Update update) {
        this.update = update;
    }

    @Override
    public EditMessageText getMessage() {
        return EditMessageText.builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .text(DialogTypes.NORMAL_FOR_YOU)
                .build();
    }
}
