package com.example.spring_api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photoUrl;
    private String email;
    private String newEmail;
    private int age;
    private String location;
    private String surname;
    private String name;
    private String password;
    private String token;
    private Date updatedAt = new Date(System.currentTimeMillis());
    private Date createdAt = new Date(System.currentTimeMillis());
    private Date deletedAt;

    public UserEntity() {
    }

    public String toJson() {
        return String.format("{\n" +
                        "\"photoUrl\":\"%s\"," +
                        "\"token\":\"%s\"," +
                        "\"id\":%d,\n" +
                        "\"email\":\"%s\"," +
                        "\"location\":\"%s\"," +
                        "\"surname\":\"%s\"," +
                        "\"name\":\"%s\"," +
                        "\"password\":\"%s\"," +
                        "\"age\":%d,\n" +
                        "\"updatedAt\":\"%s\"," +
                        "\"createdAt\":\"%s\"," +
                        "\"deletedAt\":\"%s\"" +
                        "}", getPhotoUrl(), getToken(), getId(),
                getEmail(), getLocation(), getSurname(),
                getName(), getPassword(), getAge(),
                getUpdatedAt(), getCreatedAt(), getDeletedAt());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNewEmail() { return newEmail; }

    public void setNewEmail(String newEmail) { this.newEmail = newEmail; }
}
