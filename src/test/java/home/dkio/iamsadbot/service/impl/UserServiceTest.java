package home.dkio.iamsadbot.service.impl;

import home.dkio.iamsadbot.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Test
    void getUserNameFromData() {
        UserService userService = new UserService(userRepository);

        String userNameFromData = userService.getUserNameFromData("userName= demurik");

        Assertions.assertEquals("demurik",userNameFromData);


    }
}