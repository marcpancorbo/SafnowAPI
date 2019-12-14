package client;

import model.State;
import model.Timer;
import model.User;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Client {
    public static void main(String[] args) {
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/user/19");
        Response response = webTarget.request().get();
        System.out.println(response);
        User user = response.readEntity(User.class);
        System.out.println(user);
    }
}
