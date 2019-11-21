package model;

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
    @ManyToOne (cascade = CascadeType.REMOVE)
    private User user;


}
