package home.dkio.iamsadbot.service.impl.scenarios;

import home.dkio.iamsadbot.service.impl.AbstactScenario;
import home.dkio.iamsadbot.service.impl.InlineKeyboardButtonService;
import home.dkio.iamsadbot.service.impl.UserService;
import home.dkio.iamsadbot.service.impl.WishService;
import home.dkio.iamsadbot.utils.DialogTypes;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SupportUserScenarioImpl extends AbstactScenario {

    private final UserService userService;

    private final WishService wishService;

    private final Update update;

    public SupportUserScenarioImpl(UserService userService, WishService wishService, Update update) {
        this.userService = userService;
        this.wishService = wishService;
        this.update = update;
    }

    @Override
    public EditMessageText getMessage() {
        String userName = userService.getUserNameFromData(update.getCallbackQuery().getData());
        String message = DialogTypes.WHAT_DO_YOU_WANT_TO_WISH + userName;
        return EditMessageText.builder()
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .text(message)
                .replyMarkup(InlineKeyboardButtonService.getWishes(userName, wishService.getAllWishes()))
                .build();
    }
}
