package client;

import model.State;
import model.Timer;
import model.User;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

public class Client {
    public static void main(String[] args) {
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/");
        User user = new User();
        user.setName("Name");
        Timer timer = new Timer();
        timer.setTime("20");
        timer.setState(State.ENABLED);
        user.setTimer(timer);
        webTarget.path("store/user").request().post(Entity.json(user));
    }
}
