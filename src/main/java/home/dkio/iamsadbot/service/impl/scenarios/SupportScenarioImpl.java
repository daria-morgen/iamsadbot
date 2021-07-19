package home.dkio.iamsadbot.service.impl.scenarios;

import home.dkio.iamsadbot.domain.Moods;
import home.dkio.iamsadbot.domain.User;
import home.dkio.iamsadbot.service.impl.AbstactScenario;
import home.dkio.iamsadbot.service.impl.InlineKeyboardButtonService;
import home.dkio.iamsadbot.service.impl.MoodService;
import home.dkio.iamsadbot.utils.DialogTypes;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;

import static home.dkio.iamsadbot.utils.DialogTypes.*;

public class SupportScenarioImpl extends AbstactScenario {

    MoodService moodService;

    private final Update update;

    public SupportScenarioImpl(MoodService moodService, Update update) {
        this.moodService = moodService;
        this.update = update;

    }

    @Override
    public SendMessage getMessage() {
        Set<User> users = moodService.getMoodByCode(Moods.BAD.getCode()).getUsers();
        if (users.size() == 0) {
            return SendMessage.builder().text(NO_SAD_USERS)
                    .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                    .build();
        }
        String text = DialogTypes.THANK_YOU + COUNT_SAD_USERS + users.size() + DOT + SOME_OF_THEM;
        return SendMessage.builder().text(text)
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .replyMarkup(InlineKeyboardButtonService.getFewUser(users))
                .build();
    }
}
