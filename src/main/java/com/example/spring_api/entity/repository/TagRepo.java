package com.example.spring_api.entity.repository;

import com.example.spring_api.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

public interface TagRepo  extends CrudRepository<TagEntity, Long> {
}
