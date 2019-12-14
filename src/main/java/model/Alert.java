package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Data
@Entity
public class Alert extends Identifiable {
    private String message;
    @OneToOne (cascade = CascadeType.REMOVE)
    private Ubication ubication;
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @ManyToOne (cascade = CascadeType.REMOVE)
    private User user;


}
