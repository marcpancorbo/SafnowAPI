package com.safnow.webapp;

import com.safnow.restful.RestfulConfig;
import com.safnow.security.WebSecurityConfig;
import com.safnow.controller.PersistanceController;
import com.safnow.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication (scanBasePackageClasses = {User.class, RestfulConfig.class, PersistanceController.class, WebSecurityConfig.class})
@EntityScan (basePackages = {"com/Safnow/model"})
public class App extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
