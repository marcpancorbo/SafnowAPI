package client;

import model.Alert;
import model.Ubication;
import model.User;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;


public class Client {
    public static void main(String[] args) {
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/user/2");
        User user = webTarget.request().get().readEntity(User.class);
        System.out.println(user.getName());
        for (Alert alert : user.getAlerts()){
            System.out.println(alert.getMessage());
        }
    }
    public static void testStoreUser(){
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/store/user");
        User user = new User();
        user.setName("Marc");
        user.setPhoneNumber("654023488");
        Ubication ubication = new Ubication();
        ubication.setAltitude("545454");
        ubication.setLatitude("54465465");
        Alert alert = new Alert();
        alert.setMessage("Ayuda");
        alert.setPhoneDest("622586387");
        alert.setUbication(ubication);
        alert.setUser(user);
        ubication.setAlert(alert);
        user.getAlerts().add(alert);
        webTarget.request().post(Entity.json(user));
    }
}
