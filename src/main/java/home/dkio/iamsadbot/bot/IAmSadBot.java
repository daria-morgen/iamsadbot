package home.dkio.iamsadbot.bot;

import home.dkio.iamsadbot.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class IAmSadBot extends TelegramLongPollingBot {

    private String botUserName;

    private String botToken;

    private MessageService messageService;

    public IAmSadBot(String botUserName, String botToken, MessageService messageService) {
        this.botUserName = botUserName;
        this.botToken = botToken;
        this.messageService = messageService;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Message: {}", update);
        // We check if the update has a message and the message has text
        if (update.hasMessage()) {
            try {
                execute(messageService.getRequestMessage(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            try {
                execute(messageService.getResponseMessage(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }
}
