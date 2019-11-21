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
    @OneToOne (cascade = CascadeType.REMOVE)
    private Configuration configuration;
    @OneToOne (cascade = CascadeType.REMOVE)
    private Phonebook phonebook;
    @OneToOne (cascade = CascadeType.REMOVE)
    private Timer timer;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Alert> alertSet;


}
