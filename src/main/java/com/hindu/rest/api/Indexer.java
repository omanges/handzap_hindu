package com.hindu.rest.api;

import com.hindu.rest.api.constants.Constants;
import com.hindu.rest.api.controller.controller;
import com.hindu.rest.api.model.Article;
import com.hindu.rest.api.repository.ArticleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class Indexer {

    private static final Logger logger = LogManager.getLogger(Indexer.class);

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    RestTemplate restTemplate;


    public void index(boolean force){
        ResponseEntity<String> entity;
        boolean toIndex=false;
        try {
            entity = restTemplate.getForEntity("http://localhost:9200/articles_new", String.class);
        }catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                toIndex = true;
            }
        }
        if(toIndex || force) {
            operations.putMapping(Article.class);
            //restTemplate.put("http://localhost:9200/articles_new","");
            logger.info("Loading Articles Data Please Wait...");
            getData();
            logger.info("Completed Loading Articles Data");
        }else{
            logger.info("Index was found!!!");
        }
    }

    public void deletIndex(String index_name){
        restTemplate.delete("http://localhost:9200/" + index_name);
    }

    public void reIndex(){
        index(true);
    }


    //To get all the articles using web scrapping
    @Async("ConcurrentTaskExecutor")
    private void getData(){
        int count=0;

        logger.info("Indexing " + Constants.noOfArtciles +  " Articles");
        try {
            Document document = Jsoup.connect("https://www.thehindu.com/archive/").timeout(10 * 1000).get();
            //logger.info("Started bro!!! " + document.getElementById("archiveWebContainer").select(".archiveMonthList").size());
            for(Element div : document.getElementById("archiveWebContainer").select(".archiveMonthList")){
                for (Element page1Link : div.select("a")) {
                    Document innerDocument = Jsoup.connect(page1Link.attr("href")).timeout(10 * 1000).get();
                    for (Element page2Links : innerDocument.select(".ui-state-default")){
                        Document articlesPage = Jsoup.connect(page2Links.attr("href")).timeout(10 * 1000).get();
                        for (Element articles : articlesPage.select(".archive-list")){
                            for(Element articleLink : articles.select("a")){
                                if(count!=Constants.noOfArtciles) {
                                    count++;
                                    logger.info("Count :- " + count);
                                    Document article = Jsoup.connect(articleLink.attr("href")).timeout(10 * 1000).get();
                                    logger.info(articleLink.attr("href"));
                                    String title = article.select(".article").select(".title").text().trim();
                                    String author = article.select(".article").select(".mobile-author").text().trim();
                                    String desc = getDescription(article.select(".article").select(".title").text().trim()
                                            , article.select(".article").select("div div p").text().trim());
                                    Article articleDocument = new Article(
                                            title,
                                            author,
                                            desc,
                                            count + ""
                                    );
                                    //logger.info(article.select(".article").select(".title").text());

                                    //logger.info(article.select(".article").select(".mobile-author").text());


                                    //logger.info(article.select(".article").select(".intro").text());

                                    //logger.info(article.select(".article").select("div div p").text());
                                    articleRepository.save(articleDocument);
                                }else
                                    return;
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //To append intro of article if present
    private String getDescription(String intro,String description){
        if(intro==null || intro.compareTo("")==0)
            return  description;
        else
            return (intro + " " + description);
    }
}
