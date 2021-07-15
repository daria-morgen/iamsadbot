package home.dkio.iamsadbot.service;

import home.dkio.iamsadbot.utils.ScenarioMapping;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MessageService {
    private ScenarioExecutorService scenarioExecutorService;

    public MessageService(ScenarioExecutorService scenarioExecutorService) {
        this.scenarioExecutorService = scenarioExecutorService;
    }

    public SendMessage getRequestMessage(Update update) {
        return scenarioExecutorService.execute(update.getMessage().getChatId(), update.getMessage().getFrom().getId(), update.getMessage().getFrom().getUserName());
    }

    public SendMessage getResponseMessage(Update update) {
        return scenarioExecutorService.execute(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getFrom().getId(), ScenarioMapping.mapMoodOnScenario(update.getCallbackQuery().getData()));
    }

}
