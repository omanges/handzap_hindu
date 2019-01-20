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

package com.hindu.rest.api.repository;

import com.hindu.rest.api.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    Iterable<Article> findAll();

    List<Article> findByArticleTitle(String title);

    List<Article> findByArticleAuthor(String author);

    List<Article> findByArticleDescription(String description);
}
