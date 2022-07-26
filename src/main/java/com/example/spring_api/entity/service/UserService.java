package com.example.spring_api.entity.service;

import com.example.spring_api.entity.UserEntity;
import com.example.spring_api.entity.exception.AuthorizationException;
import com.example.spring_api.entity.exception.ChangeEmailException;
import com.example.spring_api.entity.exception.UserAlreadyExistException;
import com.example.spring_api.entity.exception.UserNotFoundException;
import com.example.spring_api.entity.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("\"email\": \"Данный email уже занят!\"");
        }
        String token = getJWTToken(user.getEmail(), user.getPassword());
        user.setToken(token);
        return userRepo.save(user);
    }

    public Optional<UserEntity> getUser(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("\"id\": \"Пользователь не найден!\"");
        }
        return userRepo.findById(id);
    }

    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return null;
    }

    public UserEntity updateUser(String email, String newEmail, int age, String location, String surname, String name, String password) throws ChangeEmailException {
        UserEntity userEntity = userRepo.findByEmail(email);
        if (newEmail.equals(email)) {
            throw new ChangeEmailException("\"email\": \"Новый email не может быть таким же как старый!\"");
        }
        userEntity.setEmail(newEmail);
        userEntity.setAge(age);
        userEntity.setLocation(location);
        userEntity.setSurname(surname);
        userEntity.setName(name);
        userEntity.setPassword(password);
        userEntity.setUpdatedAt(new Date(System.currentTimeMillis()));
        return userRepo.save(userEntity);
    }

    public UserEntity loginUser(String email, String password) throws UserNotFoundException, AuthorizationException {
        UserEntity user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("\"id\": \"Пользователь не найден!\"");
        } else if (!user.getPassword().equals(password)) {
            throw new AuthorizationException("\"id\": \"Данная комбинация email и password не найдена!\"");
        }
        return user;
    }

    public String returnResponse(Boolean status) {
        String errorMessage = "Пользователь не найден!";
        return String.format("{\"success\": %b, \"errors\": {\"%s\"}}", status, errorMessage);
    }

    public String returnResponse(Boolean status, String message) {
        if (status) return String.format("{\"success\": %b, \"data\": %s}", true, message);
        else return String.format("{\"success\": %b, \"errors\": {%s}}", false, message);
    }

    private String getJWTToken(String email, String password) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setSubject(email)
                .setSubject(password)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}
