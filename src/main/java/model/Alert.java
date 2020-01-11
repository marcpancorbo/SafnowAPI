package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Objects;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Alert extends Identifiable {
    private String message;
    private String phoneDest;
    @ManyToOne (cascade = CascadeType.REFRESH)
    private User user;
    @OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
    private Ubication ubication;

}
