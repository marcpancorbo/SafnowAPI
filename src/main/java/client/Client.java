package client;

import model.SafnowDao;
import model.User;

import javax.inject.Inject;

public class Client {

    @Inject
    private static SafnowDao safnowDao;
    public static void main(String[] args) {
        User user = new User();
        safnowDao.store(user);
    }
}

