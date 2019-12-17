package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Alert extends Identifiable {
    private String message;
    private String phoneDest;
    @ManyToOne (cascade = CascadeType.ALL)
    private User user;
    @OneToOne (cascade = CascadeType.ALL)
    private Ubication ubication;

}
