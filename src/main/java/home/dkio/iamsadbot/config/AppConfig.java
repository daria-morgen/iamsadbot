package home.dkio.iamsadbot.config;

import home.dkio.iamsadbot.bot.IAmSadBot;
import home.dkio.iamsadbot.domain.Moods;
import home.dkio.iamsadbot.domain.User;
import home.dkio.iamsadbot.service.impl.MessageService;
import home.dkio.iamsadbot.service.impl.MoodService;
import home.dkio.iamsadbot.service.impl.UserService;
import home.dkio.iamsadbot.service.impl.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class AppConfig {

    @Value("${botName}")
    private String botUserName;

    @Value("${botToken}")
    private String botToken;

    @Autowired
    private MoodService moodService;
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private WishService wishService;

    @Bean
    public void iAmSadBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new IAmSadBot(botUserName, botToken, messageService));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public void generateDefaultUsers() {
        userService.saveUser(new User(-999991L, "picka4uh", moodService.getMoodByName(Moods.BAD.getName())));
        userService.saveUser(new User(-999444L, "polzen", moodService.getMoodByName(Moods.BAD.getName())));
        userService.saveUser(new User(-333399L, "demurik", moodService.getMoodByName(Moods.BAD.getName())));
//        userService.saveUser(new User(-999333L, "top1kana", moodService.getMoodByName(Moods.BAD.getName())));
    }

}
