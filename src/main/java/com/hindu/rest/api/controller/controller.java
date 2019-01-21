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

package com.hindu.rest.api.controller;

import com.hindu.rest.api.Indexer;
import com.hindu.rest.api.model.Article;
import com.hindu.rest.api.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search/article")
public class controller {

    private static final Logger logger = LogManager.getLogger(controller.class);

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    Indexer indexer;

    //To get all the authors
    @GetMapping(value = "/author")
    public List<String> getAuthors(){
        logger.info("Inside getAuthors");
      List<String> authors = new ArrayList<>();
      Iterable<Article> articlesIterable = articleRepository.findAll();
      articlesIterable.forEach(v -> {
          logger.info(v.getDescription());
          authors.add(v.getAuthor());
      });
      return authors;
    }

    //To get all articles
    @GetMapping(value = "/articles")
    public List<Article> getArticles(){
        logger.info("Inside getArticles");
        List<Article> articles = new ArrayList<>();
        Iterable<Article> articlesIterable = articleRepository.findAll();
        articlesIterable.forEach(v -> {
            articles.add(v);
        });
        return articles;
    }

    //To earch based on title
    @GetMapping(value = "/title/{title}")
    public List<Article> getArticlesByTitle(@PathVariable final String title){
        logger.info("Inside getArticlesByTitle Title " + title);
        return articleRepository.findByTitleContainingIgnoreCase(title);
    }

    //To search based on author
    @GetMapping(value = "/author/{author}")
    public List<Article> getArticlesByAuthor(@PathVariable final String author){
        logger.info("Inside getArticlesByAuthor author " + author);
        //logger.info(articleRepository.findByArtifindByArticleTitleLikecleAuthor(author).size());
        return articleRepository.findByAuthorContainingIgnoreCase(author);
    }

    //To search based on description
    @GetMapping(value = "/desc/{description}")
    public List<Article> getArticlesByDescription(@PathVariable final String description){
        logger.info("Inside getArticlesByDescription description " + description);
        return articleRepository.findByDescriptionContainingIgnoreCase(description);
    }

    //To delete index of elastic search
    @GetMapping(value = "/delete_index/{index_name}")
    public String deleteIndex(@PathVariable final String index_name){

        return index_name + " index deleted";
    }

    //To perform reindex
    @GetMapping(value = "/reindex")
    public String reindex(){
        indexer.reIndex();
        return "ReIndexing Completed";
    }
}
