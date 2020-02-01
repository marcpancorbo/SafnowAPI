package com.safnow.webapp;

import com.safnow.restful.RestfulConfig;
import com.safnow.controller.PersistanceController;
import com.safnow.model.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestfulConfig.class, PersistanceController.class, App.class},webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SafnowApplicationTests {

	@Test
	public void storeUser(){
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
