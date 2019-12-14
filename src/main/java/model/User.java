package model;

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
public class User extends Identifiable {
    public User() {
    }
    private Long id;
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
