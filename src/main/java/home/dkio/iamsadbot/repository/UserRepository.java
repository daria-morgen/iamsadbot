package home.dkio.iamsadbot.repository;

import home.dkio.iamsadbot.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select u from User u where u.tmId = ?1")
    User getUserByTmId(Long tmId);

    @Query("select count(u) from User u where u.tmId = ?1")
    int isUserExist(Long tmId);

}
