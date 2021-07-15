package home.dkio.iamsadbot.config;

import home.dkio.iamsadbot.bot.IAmSadBot;
import home.dkio.iamsadbot.service.MessageService;
import home.dkio.iamsadbot.service.MoodService;
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
    private MessageService messageService;

    @Bean
    public void iAmSadBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new IAmSadBot(botUserName, botToken, messageService));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
