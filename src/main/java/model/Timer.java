package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Timer extends Identifiable {
    public Timer() {
    }
    private String time;
    private State state;
    @OneToOne(mappedBy = "timer", cascade = CascadeType.REMOVE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private User usuario;

}
