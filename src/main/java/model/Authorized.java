package model;

import lombok.Getter;
import lombok.Setter;
import model.Identifiable;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Authorized extends Identifiable {
    private String username;
    private String password;
    private String role;

    public Authorized() {
    }

    public Authorized(String username, String password, String  roles) {
        this.username = username;
        this.password = password;
        this.role = roles;
    }


    // Getter and Setter methods
    @PostConstruct
    public void init(){
        System.out.println("Authorized init");
    }
}
