package model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Data
@Entity
public class Timer extends Identifiable {
    private String time;
    private State state;
    @OneToOne(mappedBy = "timer", cascade = CascadeType.REMOVE)
    private User usuario;

}
