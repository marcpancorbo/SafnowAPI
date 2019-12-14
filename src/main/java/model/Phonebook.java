package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Phonebook extends Identifiable {
    @ManyToMany
    private Set<User> contactos;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToOne(mappedBy = "phonebook", cascade = CascadeType.REMOVE)
    private User usuario;
}
