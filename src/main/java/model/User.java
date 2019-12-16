package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="usuario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User extends Identifiable {
    public User() {
        this.phonebook = new Phonebook();
        this.timer = new Timer();
        this.configuration = new Configuration();
    }
    private String name;
    private String phoneNumber;

    @OneToOne (cascade = CascadeType.ALL)
    private Configuration configuration;

    @OneToOne (cascade = CascadeType.ALL)
    private Phonebook phonebook;

    @OneToOne (cascade = CascadeType.ALL)
    private Timer timer;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Alert> alertSet;
}
