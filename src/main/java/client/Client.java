package client;

import model.Contact;
import model.State;
import model.Timer;
import model.User;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;


public class Client {
    public static void main(String[] args) {
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/user/1");
        User user = webTarget.request().get().readEntity(User.class);
        System.out.println(user);
    }
}
