package com.example.spring_api.entity.model;

import com.example.spring_api.entity.TagEntity;

public class Tag {
    private String color;
    private String name;
    private Long id;

    public static Tag toModel(TagEntity entity){
        Tag model = new Tag();
        model.setId(entity.getId());
        model.setColor(entity.getColor());
        model.setName(entity.getName());
        return model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
