package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@Table(name="usuario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "identifier" )
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Nameable {

    private String name;
    private static int code;
    private String phoneNumber;
    private String verificationCode;
    @Column(columnDefinition = "boolean default false")
    private boolean verificated = false;
    private String token;
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alert> alerts = new ArrayList<>();

    public User(){
    }
    public void copy(User user){
        name = user.name;
        phoneNumber = user.phoneNumber;
    }

}
