package home.dkio.iamsadbot.service.impl.scenarios;

import home.dkio.iamsadbot.domain.User;
import home.dkio.iamsadbot.service.impl.AbstactScenario;
import home.dkio.iamsadbot.service.impl.UserService;
import home.dkio.iamsadbot.service.impl.WishService;
import home.dkio.iamsadbot.utils.DialogTypes;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SendWishToUserScenarioImpl extends AbstactScenario {

    private final UserService userService;
    private final WishService wishService;
    private final Update update;

    public SendWishToUserScenarioImpl(UserService userService, WishService wishService, Update update) {
        this.userService = userService;
        this.wishService = wishService;
        this.update = update;
    }

    @Override
    public SendMessage getMessage() {
        String data = update.getCallbackQuery().getData();
        User recipient = userService.getUserByName(userService.getUserNameFromData(data));
        String wish = wishService.getWishByData(update.getCallbackQuery().getData());

        if (recipient.getTmChatId() != null) {
            String messageForUser = DialogTypes.USER + update.getCallbackQuery().getFrom().getUserName() + DialogTypes.WISHED_YOU + wish;
            getSendMessages().add(SendMessage.builder().text(messageForUser)
                    .chatId(String.valueOf(recipient.getTmChatId()))
                    .build());
        }

        String message = DialogTypes.MESSAGE_SEND_TO_USER + recipient.getName();
        return SendMessage.builder().text(message)
                .chatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()))
                .build();
    }
}
