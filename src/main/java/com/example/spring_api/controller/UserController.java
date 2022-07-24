package com.example.spring_api.controller;

import com.example.spring_api.entity.UserEntity;
import com.example.spring_api.entity.exception.UserAlreadyExistException;
import com.example.spring_api.entity.exception.UserNotFoundException;
import com.example.spring_api.entity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok().body(user);
        } catch (UserAlreadyExistException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
            return new ResponseEntity("{\"success\": false, \"errors\": {" + e.getMessage() + "}}", HttpStatus.valueOf(422));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error on saving");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"id\": \"Пользователь не найден!\"}");
        }
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserEntity user) {
        try {
            UserEntity userUpdated = userService.updateUser(user.getEmail(), user.getAge(), user.getLocation(), user.getSurname(), user.getName(), user.getPassword());
            return ResponseEntity.ok().body(userUpdated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"email\": \"Пользователь не найден!\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
//            return ResponseEntity.ok().body(userService.deleteUser(id));
            return new ResponseEntity("{\"success\": true, \"data\": \"Ок!\"}", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}