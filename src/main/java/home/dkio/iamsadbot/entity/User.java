package home.dkio.iamsadbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private Long tmId;

    @Column(nullable = false, unique = true)
    private Long tmChatId;

    @Column(nullable = false)
    private String name;

    @ManyToOne()
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Mood mood;

    public User(Long tmId, Long tmChatId, String name, Mood mood) {
        this.tmId = tmId;
        this.tmChatId=tmChatId;
        this.name = name;
        this.mood = mood;
    }

}
