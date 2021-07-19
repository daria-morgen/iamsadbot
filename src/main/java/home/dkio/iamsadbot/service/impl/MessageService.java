package home.dkio.iamsadbot.service.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public class MessageService {
    private ScenarioExecutorService scenarioExecutorService;

    private AbstactScenario abstactScenario;

    public MessageService(ScenarioExecutorService scenarioExecutorService) {
        this.scenarioExecutorService = scenarioExecutorService;
    }

    public SendMessage getRequestMessage(Update update) {
        return scenarioExecutorService.execute(update.getMessage().getChatId(), update.getMessage().getFrom().getId(), update.getMessage().getFrom().getUserName());
    }

    public AbstactScenario getResponseMessageByScenario(Update update) {
        abstactScenario = scenarioExecutorService.execute(update);
        return abstactScenario;
    }

    public List<SendMessage> getSendMessages() {
        return abstactScenario.getSendMessages();
    }
}
