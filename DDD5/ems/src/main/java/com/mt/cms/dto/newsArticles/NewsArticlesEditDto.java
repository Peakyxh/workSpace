package com.mt.cms.dto.newsArticles;


import com.mt.cms.entity.newsArticles.NewsArticles;

import java.util.List;
                                                                    import com.mt.cms.entity.publisher.Publisher;
                public class NewsArticlesEditDto{

    private NewsArticles newsArticles;


                                                                                                                                                                                                                //外键实体是：Publisher
    private List<Publisher> publisherNamePublishers;
                                                

    public  NewsArticles getNewsArticles()
    {
        return this.newsArticles;
    }
    public  void setNewsArticles(NewsArticles newsArticles)
    {
        this.newsArticles = newsArticles;
    }


                                                                                                                                                                                                                public List<Publisher> getPublisherNamePublishers()
    {
        return this.publisherNamePublishers;
    }
    public void setPublisherNamePublishers(List<Publisher> publisherNamePublishers)
    {
        this.publisherNamePublishers = publisherNamePublishers;
    }
                                                }
