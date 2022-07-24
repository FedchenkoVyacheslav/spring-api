package com.example.spring_api.entity.model;

import com.example.spring_api.entity.UserEntity;

public class User {

    private Long id;
    private String photoUrl;
    private String email;
    private int age;
    private String location;
    private String surname;
    private String name;
    private String password;
    private String token;

    public static User toModel(UserEntity entity){
        User model = new User();
        model.setId(entity.getId());
        model.setPhotoUrl(entity.getPhotoUrl());
        model.setEmail(entity.getEmail());
        model.setAge(entity.getAge());
        model.setLocation(entity.getLocation());
        model.setSurname(entity.getSurname());
        model.setName(entity.getName());
        model.setPassword(entity.getPassword());
        model.setToken(entity.getToken());
        return model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
