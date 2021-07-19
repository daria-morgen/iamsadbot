package home.dkio.iamsadbot.domain;

import lombok.Getter;

@Getter
public enum Moods {

    GOOD(
            "good",
            "хорошее"
    ),
    BAD(
            "bad",
            "плохое"
    ),
    NORMAL(
            "normal",
            "нормальное"
    ),
    EXCELLENT(
            "excellent",
            "отличное"
    ),
    CHEERFUL(
            "cheerful",
            "веселое"
    );

    private String code;
    private String name;


    Moods(String code, String name) {
        this.code = code;
        this.name = name;
    }


}
