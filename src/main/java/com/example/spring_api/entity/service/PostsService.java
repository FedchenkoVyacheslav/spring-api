package com.example.spring_api.entity.service;

import com.example.spring_api.entity.PostEntity;
import com.example.spring_api.entity.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsService {
    @Autowired
    private PostRepo postRepo;

    public Iterable<PostEntity> getPosts() {
        return postRepo.findAll();
    }

    public String returnResponse(Boolean status, String message) {
        if (status) return String.format("{\"success\":%b,\"data\":%s}", true, message);
        else return String.format("{\"success\":%b,\"errors\":{%s}}", false, message);
    }
}
