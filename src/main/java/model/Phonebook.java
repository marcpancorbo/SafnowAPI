package model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Phonebook extends Identifiable {
    @ManyToMany
    private Set<User> contactos;
    @OneToOne(mappedBy = "phonebook", cascade = CascadeType.REMOVE)
    private User usuario;
}
