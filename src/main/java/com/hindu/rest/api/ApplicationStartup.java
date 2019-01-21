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

package com.hindu.rest.api;

import com.hindu.rest.api.constants.Constants;
import com.hindu.rest.api.model.Article;
import com.hindu.rest.api.repository.ArticleRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>
{

    @Autowired
    Indexer indexer;

    @Value("${noOfArticles}")
    String noOfArtciles;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            Constants.noOfArtciles = (Integer.parseInt(noOfArtciles));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
