package home.dkio.iamsadbot.service;

import home.dkio.iamsadbot.domain.Mood;
import home.dkio.iamsadbot.domain.Moods;
import home.dkio.iamsadbot.repository.MoodRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodService {

    private MoodRepository moodRepository;

    private Moods moods;

    MoodService(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;

        if (!moodRepository.findAll().iterator().hasNext()) {
            List<Mood> moodList = new ArrayList<>();
            Arrays.stream(Moods.values()).collect(Collectors.toList()).forEach(e -> {
                moodList.add(new Mood(e.getCode(), e.getName(), null));
            });
            moodRepository.saveAll(moodList);
        }
    }

    public Iterable<Mood> getMoods() {
        return moodRepository.findAll();
    }

    public Mood getMoodByName(String data) {
        return moodRepository.getMoodIdByName(data);
    }

    public void save(Mood moodByName) {
        moodRepository.save(moodByName);
    }
}
