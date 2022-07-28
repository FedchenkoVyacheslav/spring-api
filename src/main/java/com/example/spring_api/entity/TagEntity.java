package com.example.spring_api.entity;

import com.example.spring_api.utils.DateFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TagEntity {
    public TagEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    private Date updatedAt = new Date(System.currentTimeMillis());
    private Date createdAt = new Date(System.currentTimeMillis());
    private Date deletedAt;

    public String toJson() {
        return String.format("{\"id\":%d,\"name\":\"%s\",\"color\":\"%s\"}", getId(), getName(), getColor());
    }

    public String toJson(int postId, int idTagInPost) {
        Date updatedAtInPost = new Date(System.currentTimeMillis());
        Date createdAtInPost = new Date(System.currentTimeMillis());
        Date deletedAtInPost = null;

        return String.format("{\"id\":%d,\"postId\":%d,\"tagId\":%d,\"createdAt\":\"%s\",\"updatedAt\":\"%s\",\"deletedAt\":%s," +
                        "\"tag\":{\"id\":%d,\"name\":\"%s\",\"color\":\"%s\",\"createdAt\":\"%s\",\"updatedAt\":\"%s\",\"deletedAt\":%s}}",
                idTagInPost, postId, getId(), DateFormatter.format(createdAtInPost), DateFormatter.format(updatedAtInPost), DateFormatter.format(deletedAtInPost),
                getId(), getName(), getColor(), DateFormatter.format(getCreatedAt()), DateFormatter.format(getUpdatedAt()), DateFormatter.format(getDeletedAt()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
}
