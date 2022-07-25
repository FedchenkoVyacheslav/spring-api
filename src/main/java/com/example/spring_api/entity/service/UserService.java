package com.example.spring_api.entity.service;

import com.example.spring_api.entity.UserEntity;
import com.example.spring_api.entity.exception.UserAlreadyExistException;
import com.example.spring_api.entity.exception.UserNotFoundException;
import com.example.spring_api.entity.model.User;
import com.example.spring_api.entity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("\"email\": \"Данный email уже занят!\"");
        }
        return userRepo.save(user);
    }

    public User getUser(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("\"id\": \"Пользователь не найден!\"");
        }
        return User.toModel(user);
    }

    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return null;
    }

    public UserEntity updateUser(String email, int age, String location, String surname, String name, String password) {
        UserEntity userEntity = userRepo.findByEmail(email);
        userEntity.setEmail(email);
        userEntity.setAge(age);
        userEntity.setLocation(location);
        userEntity.setSurname(surname);
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setUpdatedAt(new Date(System.currentTimeMillis()));
        return userRepo.save(userEntity);
    }
}
