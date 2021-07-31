package home.dkio.iamsadbot.service.impl.scenarios;

import home.dkio.iamsadbot.entity.Moods;
import home.dkio.iamsadbot.entity.User;
import home.dkio.iamsadbot.service.impl.AbstactScenario;
import home.dkio.iamsadbot.service.impl.InlineKeyboardButtonService;
import home.dkio.iamsadbot.service.impl.MoodService;
import home.dkio.iamsadbot.service.impl.UserService;
import home.dkio.iamsadbot.utils.DialogTypes;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;

import static home.dkio.iamsadbot.utils.DialogTypes.*;

public class SupportScenarioImpl extends AbstactScenario {

    MoodService moodService;

    UserService userService;

    private final Update update;

    public SupportScenarioImpl(MoodService moodService, UserService userService, Update update) {
        this.moodService = moodService;
        this.userService = userService;
        this.update = update;

    }

    @Override
    public EditMessageText getMessage() {
        Set<User> users = userService.getUsersByMood(moodService.getMoodByCode(Moods.BAD.getCode()));
        if (users.size() == 0) {
            return EditMessageText.builder()
                    .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                    .messageId(update.getCallbackQuery().getMessage().getMessageId())
                    .text(NO_SAD_USERS)
                    .build();
        }
        String text = DialogTypes.THANK_YOU + COUNT_SAD_USERS + users.size() + DOT + SOME_OF_THEM;

        return EditMessageText.builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .text(text)
                .replyMarkup(InlineKeyboardButtonService.getFewUser(users))
                .build();
    }
}
