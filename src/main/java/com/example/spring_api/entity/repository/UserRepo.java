package com.example.spring_api.entity.repository;

import com.example.spring_api.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
