package home.dkio.iamsadbot.service.impl;

import home.dkio.iamsadbot.entity.Mood;
import home.dkio.iamsadbot.entity.Moods;
import home.dkio.iamsadbot.service.impl.scenarios.*;
import home.dkio.iamsadbot.utils.DialogTypes;
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
        home.dkio.iamsadbot.entity.User user = new home.dkio.iamsadbot.entity.User();

        doNothing().when(userService).checkUser(update.getCallbackQuery().getFrom().getId(), update.getCallbackQuery().getMessage().getChatId(),
                update.getCallbackQuery().getMessage().getFrom().getUserName());
        when(moodService.getMoodByCode(Moods.BAD.getName().toLowerCase(Locale.ROOT))).thenReturn(mood);
        doNothing().when(userService).updateUserMood(update.getCallbackQuery().getFrom().getId(), mood);
        when(userService.getUserByTmId(update.getCallbackQuery().getFrom().getId())).thenReturn(user);
        

        AbstactScenario execute = scenarioExecutorService.execute(update);

        Assertions.assertTrue(execute instanceof BabScenarioImpl);
    }

    @Test
    void executeExcellentScenarioType() {

        update.getCallbackQuery().setData(Moods.EXCELLENT.getName());

        Mood mood = new Mood();
        home.dkio.iamsadbot.entity.User user = new home.dkio.iamsadbot.entity.User();

        doNothing().when(userService).checkUser(update.getCallbackQuery().getFrom().getId(), update.getCallbackQuery().getMessage().getChatId(),
                update.getCallbackQuery().getMessage().getFrom().getUserName());
        when(moodService.getMoodByCode(Moods.EXCELLENT.getName().toLowerCase(Locale.ROOT))).thenReturn(mood);
        doNothing().when(userService).updateUserMood(update.getCallbackQuery().getFrom().getId(), mood);
        when(userService.getUserByTmId(update.getCallbackQuery().getFrom().getId())).thenReturn(user);
        

        AbstactScenario execute = scenarioExecutorService.execute(update);

        Assertions.assertTrue(execute instanceof ExcellentScenarioImpl);
    }
    @Test
    void executeGoodScenarioType() {

        update.getCallbackQuery().setData(Moods.GOOD.getName());

        Mood mood = new Mood();
        home.dkio.iamsadbot.entity.User user = new home.dkio.iamsadbot.entity.User();

        doNothing().when(userService).checkUser(update.getCallbackQuery().getFrom().getId(), update.getCallbackQuery().getMessage().getChatId(),
                update.getCallbackQuery().getMessage().getFrom().getUserName());
        when(moodService.getMoodByCode(Moods.GOOD.getName().toLowerCase(Locale.ROOT))).thenReturn(mood);
        doNothing().when(userService).updateUserMood(update.getCallbackQuery().getFrom().getId(), mood);
        when(userService.getUserByTmId(update.getCallbackQuery().getFrom().getId())).thenReturn(user);
        

        AbstactScenario execute = scenarioExecutorService.execute(update);

        Assertions.assertTrue(execute instanceof GoodScenarioImpl);
    }
    @Test
    void executeNormalScenarioType() {

        update.getCallbackQuery().setData(Moods.NORMAL.getName());

        Mood mood = new Mood();
        home.dkio.iamsadbot.entity.User user = new home.dkio.iamsadbot.entity.User();

        doNothing().when(userService).checkUser(update.getCallbackQuery().getFrom().getId(), update.getCallbackQuery().getMessage().getChatId(),
                update.getCallbackQuery().getMessage().getFrom().getUserName());
        when(moodService.getMoodByCode(Moods.NORMAL.getName().toLowerCase(Locale.ROOT))).thenReturn(mood);
        doNothing().when(userService).updateUserMood(update.getCallbackQuery().getFrom().getId(), mood);
        when(userService.getUserByTmId(update.getCallbackQuery().getFrom().getId())).thenReturn(user);
        

        AbstactScenario execute = scenarioExecutorService.execute(update);

        Assertions.assertTrue(execute instanceof NormalScenarioImpl);
    }

    @Test
    void executeSupportScenarioType() {

        update.getCallbackQuery().setData(DialogTypes.SUPPORT);

        Mood mood = new Mood();
        home.dkio.iamsadbot.entity.User user = new home.dkio.iamsadbot.entity.User();

        doNothing().when(userService).checkUser(update.getCallbackQuery().getFrom().getId(), update.getCallbackQuery().getMessage().getChatId(),
                update.getCallbackQuery().getMessage().getFrom().getUserName());
        when(moodService.getMoodByCode(DialogTypes.SUPPORT.toLowerCase(Locale.ROOT))).thenReturn(mood);
        doNothing().when(userService).updateUserMood(update.getCallbackQuery().getFrom().getId(), mood);
        when(userService.getUserByTmId(update.getCallbackQuery().getFrom().getId())).thenReturn(user);
        

        AbstactScenario execute = scenarioExecutorService.execute(update);

        Assertions.assertTrue(execute instanceof SupportScenarioImpl);
    }

    @Test
    void executeNotSupportScenarioType() {

        update.getCallbackQuery().setData(DialogTypes.NO);

        Mood mood = new Mood();
        home.dkio.iamsadbot.entity.User user = new home.dkio.iamsadbot.entity.User();

        doNothing().when(userService).checkUser(update.getCallbackQuery().getFrom().getId(), update.getCallbackQuery().getMessage().getChatId(),
                update.getCallbackQuery().getMessage().getFrom().getUserName());
        when(moodService.getMoodByCode(DialogTypes.NO.toLowerCase(Locale.ROOT))).thenReturn(mood);
        doNothing().when(userService).updateUserMood(update.getCallbackQuery().getFrom().getId(), mood);
        when(userService.getUserByTmId(update.getCallbackQuery().getFrom().getId())).thenReturn(user);
        

        AbstactScenario execute = scenarioExecutorService.execute(update);

        Assertions.assertTrue(execute instanceof NotSupportScenarioImpl);
    }
}