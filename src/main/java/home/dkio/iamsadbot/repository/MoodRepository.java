package home.dkio.iamsadbot.repository;

import home.dkio.iamsadbot.domain.Mood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepository extends CrudRepository<Mood, Long> {

    @Query("select m from Mood m where m.name = ?1")
    Mood getMoodIdByName(String moodName);


}
