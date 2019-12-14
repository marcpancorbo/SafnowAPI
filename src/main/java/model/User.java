package model;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="usuario")
public class User extends Identifiable {
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
