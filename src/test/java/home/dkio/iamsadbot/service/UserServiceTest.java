package home.dkio.iamsadbot.service;

import home.dkio.iamsadbot.domain.Mood;
import home.dkio.iamsadbot.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class UserServiceTest {

    @Test
    void getFourRandomUser() {
        Set users = new HashSet();
        users.add(new User(-1L, "user1", new Mood()));
        users.add(new User(-2L, "user2", new Mood()));
        users.add(new User(-3L, "user3", new Mood()));
        users.add(new User(-4L, "user4", new Mood()));
        users.add(new User(-5L, "user5", new Mood()));

        List fourRandomUser = UserService.getFourRandomUser(users);
        Assertions.assertEquals(4, fourRandomUser.size());
    }
}