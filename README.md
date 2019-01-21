#handzap_hindu

This is a project based on Elastic Search and Spring Boot for exposing REST API after web scraping set of web pages.

In order to start the Application.

Step 1 :- Navigate to the root of the project directory.

Step 2 :- Extract the elasticsearch-5.6.14.zip

Step 3 :- Then navigate to elasticsearch-5.6.14 directory.

Step 4 :- Run the following command :-

Command :- ./bin/elasticsearch

Step 2 :- Run the code as normal spring boot application.

Note :- No parameters are required to run the code, the Main Class is ApiApplication.

REST API in the application are :-

To get all the articles :- http://localhost:8102/search/article/articles

To get all the authors :- http://localhost:8102/search/article/author

To perform indexing :- http://localhost:8102/search/article/reindex

To search by author :- http://localhost:8102/search/article/author/{author_name}

To search by title :- http://localhost:8102/search/article/title/{title}

To search by description :- http://localhost:8102/search/article/desc/{article_description}