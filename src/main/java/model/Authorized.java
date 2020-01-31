package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import model.Identifiable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
public class Authorized extends Identifiable {
    private static final String ROLE_PREFIX = "ROLE_";
    private String username;
    private String password;
    @JsonIgnore
    private String role;

    public Authorized() {
    }

    public Authorized(String username, String password, String  roles) {
        this.username = username;
        this.password = password;
        this.role = roles;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

        return list;
    }

    // Getter and Setter methods
    @PostConstruct
    public void init(){
        System.out.println("Authorized init");
    }
}
