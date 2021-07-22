package home.dkio.iamsadbot.service.impl;

import home.dkio.iamsadbot.service.impl.scenarios.GoodScenarioImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageServiceTest {

    private final long TEST_CHAT_ID = 1L;

    private ScenarioExecutorService scenarioExecutorService;

    private MessageService messageService;

    private final Update update;

    {
        update = new Update();
        CallbackQuery callbackQuery = new CallbackQuery();
        Message message = new Message();
        Chat chat = new Chat();
        User user = new User();

        chat.setId(TEST_CHAT_ID);
        message.setFrom(user);
        message.setChat(chat);
        callbackQuery.setMessage(message);
        update.setMessage(message);
        update.setCallbackQuery(callbackQuery);
    }

    @Before
    public void init() {
    }

    @Test
    void getRequestMessage() {
        SendMessage sendMessage = new SendMessage();
        scenarioExecutorService = mock(ScenarioExecutorService.class);
        messageService = new MessageService(scenarioExecutorService);


        when(scenarioExecutorService.execute(any(), any(), any())).thenReturn(sendMessage);

        Assertions.assertEquals(sendMessage, messageService.getRequestMessage(update));
    }

    @Test
    void getResponseMessageByScenario() {
        GoodScenarioImpl goodScenario = new GoodScenarioImpl(update);
        scenarioExecutorService = mock(ScenarioExecutorService.class);
        messageService = new MessageService(scenarioExecutorService);

        when(scenarioExecutorService.execute(update)).thenReturn(goodScenario);

        Assertions.assertEquals(String.valueOf(TEST_CHAT_ID), messageService.getResponseMessageByScenario(update).getChatId());

    }

}