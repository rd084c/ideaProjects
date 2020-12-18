package com.example.myproject;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

import static org.springframework.boot.Banner.*;

@EnableSwagger2Doc
@SpringBootApplication


//@RestController
//@EnableAutoConfiguration
public class MyprojectApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyprojectApplication.class);
        //app.setBannerMode(Mode.OFF);
        app.run(args);
        //SpringApplication.run(MyprojectApplication.class, args);
    }

}
