package home.dkio.iamsadbot.service.impl;


import home.dkio.iamsadbot.service.Scenario;
import home.dkio.iamsadbot.service.impl.scenarios.*;
import home.dkio.iamsadbot.utils.MoodsUtils;
import home.dkio.iamsadbot.utils.ScenarioMapping;
import home.dkio.iamsadbot.utils.ScenarioTypes;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.validation.constraints.NotNull;

import static home.dkio.iamsadbot.utils.DialogTypes.GREETING;
import static home.dkio.iamsadbot.utils.DialogTypes.HOW_ARE_YOU;

@Service
@Slf4j
public final class ScenarioExecutorService {

    private final UserService userService;

    private final MoodService moodService;

    public ScenarioExecutorService(UserService userService, MoodService moodService) {
        this.userService = userService;
        this.moodService = moodService;
    }

    public SendMessage execute(Long chatId, Long tmId, String userName) {

        if (userService.isNewUser(tmId,
                userName)) {
            return SendMessage.builder()
                    .chatId(String.valueOf(chatId))
                    .text(GREETING + userName + HOW_ARE_YOU)
                    .replyMarkup(InlineKeyboardButtonService.getMoodsMarkup()).build();
        }

        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(GREETING + userName + "!").build();
    }

    public AbstactScenario execute(@NotNull Update update) {
        ScenarioTypes scenarioTypes = ScenarioMapping.mapMoodOnScenario(update.getCallbackQuery().getData());
        @NonNull Long tmId = update.getCallbackQuery().getFrom().getId();

        if (MoodsUtils.getArrayOfCodes().contains(scenarioTypes.name())) {
            userService.updateUserMood(tmId,
                    moodService.getMoodByName(scenarioTypes.name())
            );
        }
        switch (scenarioTypes) {
            case EXCELLENT:
                return new ExcellentScenarioImpl(update);
            case GOOD:
                return new GoodScenarioImpl(update);
            case NORMAL:
                return new NormalScenarioImpl(update);
            case BAD:
                return new BabScenarioImpl(update);
            case SUPPORT:
                return new SupportScenarioImpl(moodService, update);
            case SUPPORT_USER:
                return new SupportUserScenarioImpl(userService, update);
            case SEND_WISH_TO_USER:
                return new SendWishToUserScenarioImpl(userService, update);
            case NOTSUPPORT:
                return null;
            default:
                return null;
        }
    }
}
