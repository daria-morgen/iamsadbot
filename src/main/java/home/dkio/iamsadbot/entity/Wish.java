package home.dkio.iamsadbot.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

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
