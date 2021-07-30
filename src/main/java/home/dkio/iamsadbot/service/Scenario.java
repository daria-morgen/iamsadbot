package home.dkio.iamsadbot.service;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface Scenario {
    EditMessageText getMessage();
}
