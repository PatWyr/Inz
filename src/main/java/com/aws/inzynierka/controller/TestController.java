package com.aws.inzynierka.controller;

import com.aws.inzynierka.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity test(@RequestParam String test) throws Exception {
        userService.getUser(test);
        return ResponseEntity.ok().build();
    }
}
