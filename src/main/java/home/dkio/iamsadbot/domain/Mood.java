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
public class Mood implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "mood", cascade = CascadeType.ALL)
    private Set<User> users;

    public Mood(String code, String name, Set<User> users) {
        this.code = code;
        this.name = name;
        this.users = users;
    }

}
