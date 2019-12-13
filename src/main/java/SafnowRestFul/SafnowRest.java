package SafnowRestFul;

import model.SafnowDaoImpl;
import model.Timer;
import model.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class SafnowRest {

    @Inject
    private SafnowDaoImpl safnowDao;
    @Path("user/{code}")
    @GET
    public User getUser(@PathParam("code") Long code){
        return safnowDao.getByKey(User.class,code);
    }
    @Path("timer/{code}")
    @GET
    public Timer getTimer(@PathParam("code") Long code){
        return safnowDao.getByKey(Timer.class, code);
    }
    @Path("test")
    @GET
    public String test(){
        return "OK";
    }
    @Path("store/user")
    @POST
    public void storeUser(){
        User user = new User();
        user.setName("Paco");
        safnowDao.storeUser(user);
    }
}
