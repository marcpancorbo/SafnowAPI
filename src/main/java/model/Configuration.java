package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Data
@Entity
public class Configuration extends Identifiable {
    private String propierty;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @OneToOne(mappedBy = "configuration", cascade = CascadeType.REMOVE)
    private User usuario;
}
