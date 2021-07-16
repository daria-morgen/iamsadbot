package home.dkio.iamsadbot.service;


import home.dkio.iamsadbot.domain.Moods;
import home.dkio.iamsadbot.domain.User;
import home.dkio.iamsadbot.utils.DialogTypes;
import home.dkio.iamsadbot.utils.MoodsUtils;
import home.dkio.iamsadbot.utils.ScenarioTypes;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.validation.constraints.NotNull;
import java.util.Set;

import static home.dkio.iamsadbot.utils.DialogTypes.*;

@Service
@Slf4j
public final class ScenarioExecutorService {

    private UserService userService;

    private MoodService moodService;

    public ScenarioExecutorService(UserService userService, MoodService moodService) {
        this.userService = userService;
        this.moodService = moodService;
    }

    public SendMessage execute(Long chatId, @NonNull Long tmId, @NotNull ScenarioTypes scenarioTypes) {
        if (MoodsUtils.getArrayOfCodes().contains(scenarioTypes.name())) {
            userService.updateUserMood(tmId,
                    moodService.getMoodByName(scenarioTypes.name())
            );
        }
        switch (scenarioTypes) {
            case EXCELLENT:
                return SendMessage.builder().text(DialogTypes.EXCELLENT_FOR_YOU)
                        .chatId(String.valueOf(chatId))
                        .replyMarkup(InlineKeyboardButtonService.getYesNoMarkup())
                        .build();
            case GOOD:
                return SendMessage.builder().text(DialogTypes.GOOD_FOR_YOU)
                        .chatId(String.valueOf(chatId))
                        .build();
            case NORMAL:
                return SendMessage.builder().text(DialogTypes.NORMAL_FOR_YOU)
                        .chatId(String.valueOf(chatId))
                        .build();
            case BAD:
                return SendMessage.builder().text(DialogTypes.BAD_FOR_YOU)
                        .chatId(String.valueOf(chatId))
                        .build();
            case SUPPORT:
                Set<User> users = moodService.getMoodByName(Moods.BAD.getName()).getUsers();
                if (users.size() == 0) {
                    return SendMessage.builder().text(NO_SAD_USERS)
                            .chatId(String.valueOf(chatId))
                            .build();
                }
                String test = DialogTypes.THANK_YOU + COUNT_SAD_USERS + users.size() + DOT + SOME_OF_THEM;
                return SendMessage.builder().text(test)
                        .chatId(String.valueOf(chatId))
                        .replyMarkup(InlineKeyboardButtonService.getFewUser(users))
                        .build();
            case NOTSUPPORT:
                return null;
            default:
                return null;
        }
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
}
