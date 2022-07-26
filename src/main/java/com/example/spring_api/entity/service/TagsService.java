package com.example.spring_api.entity.service;

import com.example.spring_api.entity.TagEntity;
import com.example.spring_api.entity.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsService {
    @Autowired
    private TagRepo tagRepo;

    public Iterable<TagEntity> getTags() {
        return tagRepo.findAll();
    }

    public String returnResponse(Boolean status, String message) {
        if (status) return String.format("{\"success\":%b,\"data\":%s}", true, message);
        else return String.format("{\"success\":%b,\"errors\":{%s}}", false, message);
    }
}
