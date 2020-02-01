package com.safnow.client;

import com.safnow.model.Alert;
import com.safnow.model.Ubication;
import com.safnow.model.User;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;


public class Client {
    public static void main(String[] args) {
        testStoreUser();
    }

    public static void testStoreUser() {
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/store/user");
        User user = new User();
        user.setName("Identified");
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

    public static void testStoreAlert() {
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/user/User0");
        User user = webTarget.request().get().readEntity(User.class);
        System.out.print(user.toString());
        Alert alert = new Alert();
        alert.setUser(user);
        alert.setMessage("Prueba ayuda");
        webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/store/alert");
        webTarget.request().post(Entity.json(alert));
    }

    public static void deleteAlert() {
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/user/User0");
        User user = webTarget.request().get().readEntity(User.class);
        Alert alert = user.getAlerts().get(0);
        System.out.println(alert.toString());
        webTarget = ClientBuilder.newClient().target("http://localhost:8080/rest/delete/alert");
        webTarget.request().post(Entity.json(alert));
    }
}
