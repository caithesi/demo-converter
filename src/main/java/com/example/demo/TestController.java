package com.example.demo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    private final PhoneRepo phoneRepo;

    private final UserRepo userRepo;

    @GetMapping
    @Transactional(rollbackOn = Exception.class)
    public void test() {
        User user = userRepo.findById(0).get();
        PhoneNumber phoneNumber = user.getPhoneNumber();
        int x = 1;
    }
}
