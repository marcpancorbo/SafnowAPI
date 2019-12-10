package model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Data
@Entity
public class Configuration extends Identifiable {
    private String propierty;
    @OneToOne(mappedBy = "configuration", cascade = CascadeType.REMOVE)
    private User usuario;
}
