package model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Timer extends Identifiable {
    public Timer() {
        this.time = "20";
        this.state = State.DISABLED;
    }
    private String time;
    private State state;

    @OneToOne(mappedBy = "timer", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private User usuario;

}
