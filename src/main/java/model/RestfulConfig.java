package model;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("rest")
public class RestfulConfig extends ResourceConfig {
    public RestfulConfig(){
        register(SafnowRest.class);
    }
}
