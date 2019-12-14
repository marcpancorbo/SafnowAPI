package SafnowRestFul;

import model.SafnowDaoImpl;
import model.Timer;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * Este método permite recibir un json con un objeto User en el body de la petición e insertarlo en la bbdd
     * @param user
     * @return
     */
    @Path("store/user/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> storeUser(User user){
        safnowDao.storeUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
