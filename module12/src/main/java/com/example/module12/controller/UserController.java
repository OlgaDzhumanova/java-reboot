package com.example.module12.controller;

import com.example.module12.entity.User;
import com.example.module12.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
//@Tag(name = "User", description = "User API")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAllCars() {
        List<User> cars = service.findAll();
        logger.info("getting user list: {}", cars);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    public User getCarById(@PathVariable("id") long userId) {
        return service.findById(userId);
    }

    @PostMapping
    public ResponseEntity<User> createCar(@RequestBody User user) {
        service.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/users/" + user.getId());
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateCar(@RequestBody User user) {
        service.update(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteCarById(@PathVariable long userId) {
        service.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
