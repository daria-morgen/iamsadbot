package home.dkio.iamsadbot.domain;

import lombok.Getter;

@Getter
public enum Wishes {
    DO_NOT_BE_SAD(
            1l, "Не грусти, друг!"
    ),
    EVERYTHING_WILL_BE_FINE(
            2l, "Всё будет хорошо."
    ),
    EVERYTHING_WILL_BE_OK(
            3l, "У тебя всё наладится!"
    ),
    WHITE_STRIPE(
            4l, "Черная полоса сменится белой."
    ),
    POWER(
            5l, "Ты гораздо сильнее чем думаешь."
    ),
    EVERYTHING_PASSES(
            6l, "Всё трудности - это временно."
    );

    private Long id;
    private String name;


    Wishes(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
