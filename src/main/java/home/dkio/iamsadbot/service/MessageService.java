package home.dkio.iamsadbot.service;

import home.dkio.iamsadbot.scenario.Scenario;
import home.dkio.iamsadbot.utils.MoodsUtils;
import home.dkio.iamsadbot.utils.ScenarioMapping;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static home.dkio.iamsadbot.utils.DialogTypes.GREETING;
import static home.dkio.iamsadbot.utils.DialogTypes.HOW_ARE_YOU;

@Service
public class MessageService {

    private UserService userService;

    private MoodService moodService;

    public MessageService(UserService userService, MoodService moodService) {
        this.userService = userService;
        this.moodService = moodService;
    }

    public SendMessage getRequestMessage(Update update) {
        if (userService.isNewUser(update.getMessage().getFrom().getId(),
                update.getMessage().getFrom().getUserName())) {
            return SendMessage.builder()
                    .chatId(String.valueOf(update.getMessage().getChatId()))
                    .text(GREETING + update.getMessage().getFrom().getUserName() + HOW_ARE_YOU)
                    .replyMarkup(InlineKeyboardButtonService.getMoodsMarkup()).build();
        }

        return SendMessage.builder()
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .text("Привет, " + update.getMessage().getFrom().getUserName() + ", как твое настроение сегодня?")
                .replyMarkup(InlineKeyboardButtonService.getMoodsMarkup()).build();
    }

    public SendMessage getResponseMessage(Update update) {
        if (MoodsUtils.getArrayOfCodes().contains(update.getCallbackQuery().getData())) {
            userService.updateUserMood(update.getCallbackQuery().getFrom().getId(),
                    moodService.getMoodByName(update.getCallbackQuery().getData())
            );
        }

        return Scenario.execute(update.getCallbackQuery().getFrom().getId(), ScenarioMapping.mapMoodOnScenario(update.getCallbackQuery().getData()));
    }

}
