package org.softwire.training.bookish.restService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class RestService {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestService.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
        app.run();
//        SpringApplication.run(RestService.class, args);
    }

}
