package home.dkio.iamsadbot.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@ToString
@RequiredArgsConstructor
@Getter
@Setter
public class Wish implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public Wish(String name) {
        this.name = name;
    }

}
