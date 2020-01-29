package SafnowRestFul;

import model.Alert;
import model.SafnowDaoImpl;
import model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Service
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
public class SafnowRest {

    @Inject
    private SafnowDaoImpl safnowDao;
    @Path("user")
    @GET
    public List<User> findUsers(){
        return safnowDao.findUsers();
    }
    @Path("user/{code}")
    @GET
    public User getUser(@PathParam("code") String code) {
        User user = safnowDao.getUser(code);
        if (user == null)
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND).entity("User not found").build());
        return user;
    }

    @Path("test")
    @GET
    public String test() {
        return "Prueba git";
    }

    /**
     * Este método permite recibir un json con un objeto User en el body de la petición e insertarlo en la bbdd además de actualizar el usuario en caso de que ya exista
     *
     * @param user
     * @return
     */
    @Path("store/user/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public User storeUser(User user) {
        try {
            if (user.getIdentifier() != null && user.getId() != null){
                User user1 = getUser(user.getIdentifier());
               if ( user1 != null){
                   user1.copy(user);
                   user = user1;
               }
            }else{
                user.setIdentifier(safnowDao.getNextidentifier());
            }
          safnowDao.storeUser(user);
            return user;
        } catch (DataIntegrityViolationException ex) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).entity("Duplicated entry for " + user.getIdentifier()).build());
        }
    }

    @Path("store/alert/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void storeAlert(Alert alert) {
        safnowDao.storeAlert(alert);
    }

    @Path("delete/alert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteAlert(Alert alert) {
        safnowDao.deleteAlert(alert);
    }

    @Path("delete/user")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(User user){safnowDao.deleteUser(user);}
    @Path("code")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public String getCode(){
        return safnowDao.getCode();
    }

}
