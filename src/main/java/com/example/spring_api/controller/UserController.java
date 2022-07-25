package com.example.spring_api.controller;

import com.example.spring_api.entity.UserEntity;
import com.example.spring_api.entity.exception.ChangeEmailException;
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
            return ResponseEntity.ok().body("{\"success\": true, \"data\":" + user.toJson() + "}");
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity("{\"success\": false, \"errors\": {" + e.getMessage() + "}}", HttpStatus.valueOf(422));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error on saving");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body("{\"success\": true, \"data\":" + userService.getUser(id).get().toJson() + "}");
        } catch (UserNotFoundException e) {
            return new ResponseEntity("{\"success\": false, \"errors\": {" + e.getMessage() + "}}", HttpStatus.valueOf(422));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"id\": \"Пользователь не найден!\"}");
        }
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserEntity user) {
        try {
            UserEntity userUpdated = userService.updateUser(user.getEmail(), user.getNewEmail(), user.getAge(), user.getLocation(),
                    user.getSurname(), user.getName(), user.getPassword());
            return ResponseEntity.ok().body("{\"success\": true, \"data\":" + userUpdated.toJson() + "}");
        } catch (ChangeEmailException e) {
            return new ResponseEntity("{\"success\": false, \"errors\": {" + e.getMessage() + "}}", HttpStatus.valueOf(422));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"email\": \"Пользователь не найден!\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity("{\"success\": true, \"data\": \"Ок!\"}", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"email\": \"Пользователь не найден!\"}");
        }
    }
}