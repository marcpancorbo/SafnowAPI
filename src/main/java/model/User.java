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

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String phoneNumber;
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Configuration configuration;
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Phonebook phonebook;
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Timer timer;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Alert> alertSet;


}
