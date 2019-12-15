package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Phonebook extends Identifiable {
    public Phonebook() {
        this.contacts = new ArrayList<>();
    }

    @OneToMany(mappedBy = "phonebook", cascade = CascadeType.ALL)

    @JsonBackReference
    private List<Contact> contacts;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToOne(mappedBy = "phonebook", cascade = CascadeType.ALL)
    private User usuario;
}
