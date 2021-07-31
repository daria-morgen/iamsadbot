package home.dkio.iamsadbot.service;

import home.dkio.iamsadbot.entity.Mood;
import home.dkio.iamsadbot.entity.User;
import home.dkio.iamsadbot.service.impl.InlineKeyboardButtonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.HashSet;
import java.util.Set;

class InlineKeyboardButtonServiceTest {

    @Test
    public void successGetFourUsers() {
        Set users = new HashSet();
        users.add(new User(-1L, null, "user1", new Mood()));
        users.add(new User(-2L, null, "user2", new Mood()));
        users.add(new User(-3L, null, "user3", new Mood()));
//        users.add(new User(-4L, "user4", new Mood()));

        ReplyKeyboard fewUser = InlineKeyboardButtonService.getFewUser(users);
        Assertions.assertNotNull(fewUser);
    }

}