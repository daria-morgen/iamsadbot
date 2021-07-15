package home.dkio.iamsadbot.service;

import home.dkio.iamsadbot.domain.Mood;
import home.dkio.iamsadbot.domain.User;
import home.dkio.iamsadbot.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
