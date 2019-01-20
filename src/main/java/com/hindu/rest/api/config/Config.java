package com.hindu.rest.api.config;

import com.hindu.rest.api.Indexer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;

@Configuration
public class Config {

    @Bean(name = "ConcurrentTaskExecutor")
    public TaskExecutor getTaskExecutor () {
        return new ConcurrentTaskExecutor(
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    @Bean(name = "restTemplate")
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean(name = "Indexer")
    public Indexer getIndexer(){
        return new Indexer();
    }
}
