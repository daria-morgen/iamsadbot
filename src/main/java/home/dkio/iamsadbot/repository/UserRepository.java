package home.dkio.iamsadbot.repository;

import home.dkio.iamsadbot.domain.Mood;
import home.dkio.iamsadbot.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.tmId = ?1")
    User getUserByTmId(Long tmId);

    @Query("select count(u) from User u where u.tmId = ?1")
    int isUserExist(Long tmId);

    @Query("select u from User u where u.name = ?1")
    User getUserByName(String userName);

//    @Query("select u from User u where u.name = ?1")
    Set<User> getUsersByMood(Mood mood);
}
