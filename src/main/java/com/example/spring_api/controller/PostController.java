package com.example.spring_api.controller;

import com.example.spring_api.entity.PostEntity;
import com.example.spring_api.entity.TagEntity;
import com.example.spring_api.entity.service.PostsService;
import com.example.spring_api.entity.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/posts", produces = "application/json")
public class PostController {

    @Autowired
    private PostsService postsService;

    @CrossOrigin(origins = "http://localhost:8082")
    @GetMapping
    public ResponseEntity getTags() {

        Iterable<PostEntity> tags = postsService.getPosts();
        String res = "";
        for (PostEntity tag : tags) {
            res += tag.toJson() + ",";
        }
        return ResponseEntity.ok().body(postsService.returnResponse(true, "[" + res.substring(0, res.length() - 1) + "]"));
    }
}
