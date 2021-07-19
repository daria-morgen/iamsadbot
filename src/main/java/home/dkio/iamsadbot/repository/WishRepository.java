package home.dkio.iamsadbot.repository;

import home.dkio.iamsadbot.domain.Wish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends CrudRepository<Wish, Long> {
}
