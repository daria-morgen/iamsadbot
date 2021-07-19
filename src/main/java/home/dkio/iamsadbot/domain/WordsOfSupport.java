package home.dkio.iamsadbot.domain;

import lombok.Getter;

@Getter
public enum WordsOfSupport {
    DO_NOT_BE_SAD(
            "Не грусти, друг!"
    ),
    EVERYTHING_WILL_BE_FINE(
            "Всё будет хорошо."
    ),
    EVERYTHING_WILL_BE_OK(
            "У тебя всё наладится!"
    ),
    WHITE_STRIPE(
            "Черная полоса сменится белой."
    ),
    POWER(
            "Ты гораздо сильнее чем думаешь."
    ),
    EVERYTHING_PASSES(
            "Всё трудности - это временно."
    );

    private String name;


    WordsOfSupport(String name) {
        this.name = name;
    }
}
