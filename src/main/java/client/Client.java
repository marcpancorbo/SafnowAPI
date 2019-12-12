package client;

import model.SafnowDaoImpl;
import model.User;

import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class Client {
private static WebTarget target;
    @Inject
    private static SafnowDaoImpl safnowDao;
    public static void main(String[] args) {
        target = ClientBuilder.newClient().target("http://localhost:8080/rest");

        User user = new User();
        user.setName("MARC");
        safnowDao.store(user);
    }
}

