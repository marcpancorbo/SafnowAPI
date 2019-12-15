package model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Contact extends Identifiable{
    private String name;
    private String phoneNumber;
    private boolean favorite;

    @ManyToOne
    private Phonebook phonebook;

    public Contact() {
        this.favorite = false;
    }
}
