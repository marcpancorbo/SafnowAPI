package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Phonebook extends Identifiable {
    public Phonebook() {
        this.contacts = new ArrayList<>();
    }

    @OneToMany(mappedBy = "phonebook", cascade = CascadeType.ALL)

    @JsonBackReference
    private List<Contact> contacts;


    @OneToOne(mappedBy = "phonebook", cascade = CascadeType.ALL)
    @JsonIgnore
    private User usuario;
}
