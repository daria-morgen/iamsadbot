package home.dkio.iamsadbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Scenario {
    SendMessage getMessage();
}
