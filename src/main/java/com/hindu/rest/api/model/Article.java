/*
 * ******************************************************
 *  * Copyright (c) 2019.  Omkar Mestry om.m.mestry@gmail.com
 *  *
 *  * This file is part of REST HINDU API.
 *  *
 *  * REST HINDU API can not be copied and/or distributed without the express
 *  * permission of Omkar Mestry
 *  ******************************************************
 *
 */

package com.hindu.rest.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hindu.rest.api.constants.Constants;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document(indexName = Constants.indexName, type = Constants.indextype)
public class Article {

    @Id
    @JsonProperty("id")
    private String id;

    @NotNull
    @JsonProperty("author")
    private String author;

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;


    public Article(String title, String author, String description,String id) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Article(){


    }




}
