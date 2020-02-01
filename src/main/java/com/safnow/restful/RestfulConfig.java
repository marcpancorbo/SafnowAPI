package com.safnow.restful;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("rest")
public class RestfulConfig extends ResourceConfig {
    public RestfulConfig(){
    }
    @PostConstruct
    private void setup(){
        register(SafnowRest.class);
        System.out.println("INIT");
    }
}
