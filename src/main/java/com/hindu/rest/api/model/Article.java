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
    private String articleAuthor;

    @NotNull
    @JsonProperty("title")
    private String articleTitle;

    @NotNull
    @JsonProperty("description")
    private String articleDescription;

    public Article(){

    }

    public Article(String title, String author, String description,String id) {
        this.articleTitle = title;
        this.articleAuthor = author;
        this.articleDescription = description;
        this.id = id;
    }

    public String getTitle() {
        return this.articleTitle;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.articleTitle= title;
    }

    public String getAuthor() {
        return this.articleAuthor;
    }

    public void setAuthor(String author) {
        this.articleAuthor = author;
    }

    public String getDescription() {
        return this.articleDescription;
    }

    public void setDescription(String description) {
        this.articleDescription = description;
    }



}
