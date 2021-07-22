package home.dkio.iamsadbot.service.impl;


import home.dkio.iamsadbot.domain.Mood;
import home.dkio.iamsadbot.domain.User;
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
import java.util.Locale;

import static home.dkio.iamsadbot.utils.DialogTypes.GREETING;
import static home.dkio.iamsadbot.utils.DialogTypes.HOW_ARE_YOU;

@Service
@Slf4j
public class ScenarioExecutorService {

    private final UserService userService;

    private final MoodService moodService;

    private final WishService wishService;

    public ScenarioExecutorService(UserService userService, MoodService moodService, WishService wishService) {
        this.userService = userService;
        this.moodService = moodService;
        this.wishService = wishService;
    }

    public SendMessage execute(Long tmChatId, Long tmId, String userName) {

        userService.checkUser(tmId, tmChatId,
                userName);

        return SendMessage.builder()
                .chatId(String.valueOf(tmChatId))
                .text(GREETING + userName + HOW_ARE_YOU)
                .replyMarkup(InlineKeyboardButtonService.getMoodsMarkup()).build();

    }

    public AbstactScenario execute(@NotNull Update update) {
        ScenarioTypes scenarioTypes = ScenarioMapping.mapMoodOnScenario(update.getCallbackQuery().getData());
        @NonNull Long tmId = update.getCallbackQuery().getFrom().getId();

        userService.checkUser(tmId, update.getCallbackQuery().getMessage().getChatId(),
                update.getCallbackQuery().getMessage().getFrom().getUserName());

        if (MoodsUtils.getArrayOfCodes().contains(scenarioTypes.name().toLowerCase(Locale.ROOT))) {
            Mood moodByCode = moodService.getMoodByCode(scenarioTypes.name().toLowerCase(Locale.ROOT));
            userService.updateUserMood(tmId,
                    moodByCode
            );
            User userByTmId = userService.getUserByTmId(tmId);
            moodService.updateUserMood(moodByCode, userByTmId);
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
                return new SupportUserScenarioImpl(userService, wishService, update);
            case SEND_WISH_TO_USER:
                return new SendWishToUserScenarioImpl(userService, wishService, update);
            case NOTSUPPORT:
                return new SendWishToUserScenarioImpl(userService, wishService, update);
            default:
                return null;
        }
    }
}
