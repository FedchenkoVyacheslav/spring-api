package com.example.spring_api.controller;

import com.example.spring_api.entity.UserEntity;
import com.example.spring_api.entity.exception.AuthorizationException;
import com.example.spring_api.entity.exception.ChangeEmailException;
import com.example.spring_api.entity.exception.UserAlreadyExistException;
import com.example.spring_api.entity.exception.UserNotFoundException;
import com.example.spring_api.entity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:8082")
    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok().body(userService.returnResponse(true, user.toJson()));
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity(userService.returnResponse(false, e.getMessage()), HttpStatus.valueOf(422));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(userService.returnResponse(false, "\"Error on saving!\""));
        }
    }

    @CrossOrigin(origins = "http://localhost:8082")
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userService.returnResponse(true, userService.getUser(id).get().toJson()));
        } catch (UserNotFoundException e) {
            return new ResponseEntity(userService.returnResponse(false, e.getMessage()), HttpStatus.valueOf(422));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(userService.returnResponse(false));
        }
    }

    @CrossOrigin(origins = "http://localhost:8082")
    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserEntity user) {
        try {
            UserEntity userUpdated = userService.updateUser(user.getEmail(), user.getNewEmail(), user.getAge(), user.getLocation(),
                    user.getSurname(), user.getName(), user.getPassword());
            return ResponseEntity.ok().body(userService.returnResponse(true, userUpdated.toJson()));
        } catch (ChangeEmailException e) {
            return new ResponseEntity(userService.returnResponse(false, e.getMessage()), HttpStatus.valueOf(422));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(userService.returnResponse(false));
        }
    }

    @CrossOrigin(origins = "http://localhost:8082")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity(userService.returnResponse(true, "\"Ok!\""), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(userService.returnResponse(false));
        }
    }

    @CrossOrigin(origins = "http://localhost:8082")
    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            UserEntity user = userService.loginUser(email, password);
            return ResponseEntity.ok().body(userService.returnResponse(true, user.toJson()));
        } catch (UserNotFoundException | AuthorizationException e) {
            return new ResponseEntity(userService.returnResponse(false, e.getMessage()), HttpStatus.valueOf(422));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(userService.returnResponse(false, "\"Error!\""));
        }
    }
}