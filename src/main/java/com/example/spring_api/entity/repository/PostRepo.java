package com.example.spring_api.entity.repository;

import com.example.spring_api.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<PostEntity, Long> {
}
