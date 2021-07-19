package home.dkio.iamsadbot.service.impl;

import home.dkio.iamsadbot.domain.Mood;
import home.dkio.iamsadbot.domain.User;
import home.dkio.iamsadbot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByTmId(Long tmId) {
        return userRepository.getUserByTmId(tmId);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean isUserExist(Long tmId) {
        return userRepository.isUserExist(tmId) > 0;
    }

    public boolean isNewUser(Long tmId, String userName) {
        if (isUserExist(tmId)) {
            return true;
        } else {
            User user = new User(tmId, userName, null);
            saveUser(user);
            return false;
        }
    }

    public void updateUserMood(Long tmId, Mood moodByName) {
        User user = getUserByTmId(tmId);
        user.setMood(moodByName);
        saveUser(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public static List<User> getFourRandomUser(Set<User> users) {
        List<User> randomUsers = new ArrayList();
        List<User> allUsers = new ArrayList(users);
        int randomUserListSize = 0;
        if (allUsers.size() >= 4) {
            randomUserListSize = 4;
        } else randomUserListSize = 2;
        while (randomUsers.size() < randomUserListSize) {
            if (allUsers.size() == 4) {
                randomUsers.addAll(allUsers);
            }
            int randomWithMathRandom = 0;
            randomWithMathRandom = (int) ((Math.random() * (allUsers.size() - 1)) + 1);
            if (!randomUsers.contains(allUsers.get(randomWithMathRandom)))
                randomUsers.add(allUsers.get(randomWithMathRandom));

        }
        return randomUsers;
    }

    public String getUserNameFromData(String data) {
        return data.substring(data.indexOf("user = ") + 1);
    }
}
