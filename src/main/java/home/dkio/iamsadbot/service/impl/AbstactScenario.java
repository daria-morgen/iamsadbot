package home.dkio.iamsadbot.service.impl;

import home.dkio.iamsadbot.service.Scenario;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstactScenario implements Scenario {
    List<SendMessage> sendMessages = new ArrayList<>();
}
