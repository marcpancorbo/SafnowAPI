package model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Ubication extends Identifiable {
    private String altitude;
    private String latitude;
    @OneToOne(mappedBy = "ubication",cascade = CascadeType.REMOVE)
    private Alert alert;
}
