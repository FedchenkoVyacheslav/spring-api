package com.example.spring_api.controller;

import com.example.spring_api.entity.TagEntity;
import com.example.spring_api.entity.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tags", produces = "application/json")
public class TagController {

    @Autowired
    private TagsService tagsService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8082/tags")
    public ResponseEntity getTags() {

        Iterable<TagEntity> tags = tagsService.getTags();
        String res = "";
        for (TagEntity tag : tags) {
            res += tag.toJson() + ",";
        }
        return ResponseEntity.ok().body(tagsService.returnResponse(true, "[" + res.substring(0, res.length() - 1) + "]"));
    }
}
