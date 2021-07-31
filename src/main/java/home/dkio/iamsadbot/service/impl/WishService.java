package home.dkio.iamsadbot.service.impl;

import home.dkio.iamsadbot.entity.Wish;
import home.dkio.iamsadbot.entity.Wishes;
import home.dkio.iamsadbot.repository.WishRepository;
import home.dkio.iamsadbot.utils.KeyIdentifiers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishService {

    private WishRepository wishRepository;

    WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;

        if (!wishRepository.findAll().iterator().hasNext()) {
            List<Wish> wishList = new ArrayList<>();
            Arrays.stream(Wishes.values()).collect(Collectors.toList()).forEach(e -> {
                wishList.add(new Wish(e.getName()));
            });
            wishRepository.saveAll(wishList);
        }
    }

    public Iterable<Wish> getAllWishes() {
        return wishRepository.findAll();
    }

    public String getWishByData(String data) {
        Long wishId = Long.valueOf(data.substring(data.indexOf(KeyIdentifiers.id) + KeyIdentifiers.id.length(), KeyIdentifiers.id.length() + 1));
        return wishRepository.findById(wishId).get().getName();
    }
}
