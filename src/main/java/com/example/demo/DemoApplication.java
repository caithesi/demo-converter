package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PhoneRepo phoneRepo;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneRepo.save(phoneNumber);
        User user = new User();
        user.setName("a");
        user.setInfo(List.of(
          new User.Info("1"),
          new User.Info("2")

        ));
        user.setPhoneNumber(phoneNumber);
        userRepo.save(user);
    }

}
