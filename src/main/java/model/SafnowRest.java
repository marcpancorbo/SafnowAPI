package model;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Service
public class SafnowRest implements Serializable {
    @Inject
    private SafnowDaoImpl safnowDao;

    @Path("user/{code}")
    @GET
    public User getUser(@PathParam("code") String code){
        return safnowDao.getUser(code);
    }


}
