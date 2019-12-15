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
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/store/user");
        User user = new User();
        user.setName("PruebaSaf");
        user.setPhoneNumber("654023488");
        Contact contact = new Contact();
        contact.setName("Paco");
        contact.setPhoneNumber("666222333");
        contact.setPhonebook(user.getPhonebook());
        webTarget.request().post(Entity.json(user));
    }
}
