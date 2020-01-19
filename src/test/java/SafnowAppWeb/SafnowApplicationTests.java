package SafnowAppWeb;

import SafnowRestFul.RestfulConfig;
import SafnowRestFul.SafnowRest;
import controller.PersistanceController;
import model.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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
