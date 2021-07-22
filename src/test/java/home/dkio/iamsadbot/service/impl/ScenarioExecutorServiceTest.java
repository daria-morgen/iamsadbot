package home.dkio.iamsadbot.service.impl;

import home.dkio.iamsadbot.domain.Mood;
import home.dkio.iamsadbot.domain.Moods;
import home.dkio.iamsadbot.service.impl.scenarios.BabScenarioImpl;
import home.dkio.iamsadbot.utils.ScenarioTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ScenarioExecutorServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private MoodService moodService;
    @Mock
    private WishService wishService;

    ScenarioExecutorService scenarioExecutorService;

    ScenarioTypes scenarioTypes;

    private final Update update;

    {
        update = new Update();
        CallbackQuery callbackQuery = new CallbackQuery();
        Message message = new Message();
        Chat chat = new Chat();
        User user = new User();
        user.setId(999L);

        chat.setId(1L);
        message.setFrom(user);
        message.setChat(chat);
        callbackQuery.setMessage(message);
        callbackQuery.setFrom(user);
        update.setMessage(message);
        update.setCallbackQuery(callbackQuery);
    }

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        moodService = mock(MoodService.class);
        wishService = mock(WishService.class);
        scenarioExecutorService = new ScenarioExecutorService(userService, moodService, wishService);
    }

    @Test
    void executeBadScenarioType() {

        update.getCallbackQuery().setData(Moods.BAD.getName());

        Mood mood = new Mood();
        home.dkio.iamsadbot.domain.User user = new home.dkio.iamsadbot.domain.User();

        doNothing().when(userService).checkUser(update.getCallbackQuery().getFrom().getId(), update.getCallbackQuery().getMessage().getChatId(),
                update.getCallbackQuery().getMessage().getFrom().getUserName());
        when(moodService.getMoodByCode(Moods.BAD.getName().toLowerCase(Locale.ROOT))).thenReturn(mood);
        doNothing().when(userService).updateUserMood(update.getCallbackQuery().getFrom().getId(), mood);
        when(userService.getUserByTmId(update.getCallbackQuery().getFrom().getId())).thenReturn(user);
        doNothing().when(moodService).updateUserMood(any(), any());

        AbstactScenario execute = scenarioExecutorService.execute(update);

        Assertions.assertTrue(execute instanceof BabScenarioImpl);
    }
}