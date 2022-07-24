package com.example.spring_api.controller;

import com.example.spring_api.entity.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tags", produces = "application/json")
public class TagController {

    @Autowired
    private TagsService tagsService;

    @GetMapping
    public ResponseEntity getTags() {
        return ResponseEntity.ok().body(tagsService.getTags());
    }
}
