package home.dkio.iamsadbot.bot;

import home.dkio.iamsadbot.service.impl.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
                SendMessage message = messageService.getResponseMessageByScenario(update).getMessage();
                execute(message);
                if (messageService.getSendMessages().size() > 0) {
                    for (SendMessage e : messageService.getSendMessages()) {
                        execute(e);
                    }
                    messageService.getSendMessages().clear();
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }
}
